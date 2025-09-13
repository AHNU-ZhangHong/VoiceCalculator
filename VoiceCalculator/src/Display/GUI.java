package Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Calculate.Transform;
import Voice.playSound;
import Voice.readResult;

public class GUI {
	JFrame frame;
	JPanel display, function, button;
	JTextField field;
	JLabel History, transform, Mute;
	JButton[] buttonNumber, buttonOperate, buttonLetter;
	JButton buttonClear;
	
	
	boolean isMute=false;
	boolean isNew=false;
	String expression="";
	String result;
	
	public GUI(){init();}
	
	private void init() {
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(360,552);
		frame.setLayout(new FlowLayout());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		display=new JPanel();
		function=new JPanel();
		button=new JPanel();
		
		field=new JTextField();
		
		buttonNumber=new JButton[11];
		for(int i=0; i<10; i++) {
			int index=i;
			buttonNumber[i]=new JButton(String.valueOf(i));
			buttonNumber[i].setBackground(Color.white);
			buttonNumber[i].setFont(Format.font_number);
			buttonNumber[i].setFocusPainted(false);
			buttonNumber[i].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					buttonNumber[index].setBackground(Color.lightGray);
				}
				public void mouseExited(MouseEvent e) {
					buttonNumber[index].setBackground(Color.white);
				}
			});
			buttonNumber[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(isNew==true) {
						field.setText(null);
						isNew=false;
					}
					String tmp=field.getText()+String.valueOf(index);
					field.setText(tmp);
					expression=expression+String.valueOf(index);
					if(isMute==true) return;
					String path="/sounds/"+String.valueOf(index)+".wav";
					playSound.run(path);
				}
			});
		}
		
		buttonNumber[10]=new JButton(".");
		buttonNumber[10].setBackground(Color.white);
		buttonNumber[10].setFont(Format.font_number);
		buttonNumber[10].setFocusPainted(false);
		buttonNumber[10].addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				buttonNumber[10].setBackground(Color.lightGray);
			}
			public void mouseExited(MouseEvent e) {
				buttonNumber[10].setBackground(Color.white);
			}
		});
		buttonNumber[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+".";
				field.setText(tmp);
				expression=expression+".";
				if(isMute==true) return;
				String path="/sounds/point.wav";
				playSound.run(path);
			}
		});
		
		buttonOperate=new JButton[5];
		buttonOperate[0]=new JButton("+");
		buttonOperate[1]=new JButton("-");
		buttonOperate[2]=new JButton("×");
		buttonOperate[3]=new JButton("÷");
		buttonOperate[4]=new JButton("=");
		for(int i=0; i<4; i++) buttonOperate[i].setBackground(Color.green);
		buttonOperate[4].setBackground(Color.cyan);
		for(int i=0; i<4; i++) buttonOperate[i].setFont(Format.font_bigLetter);
		for(int i=0; i<4; i++) {
			int index=i;
			buttonOperate[i].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					buttonOperate[index].setBackground(Color.white);
				}
				public void mouseExited(MouseEvent e) {
					buttonOperate[index].setBackground(Color.green);
				}
			});
		}
		buttonOperate[4].addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				buttonOperate[4].setBackground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				buttonOperate[4].setBackground(Color.cyan);
			}
		});
		for(int i=0; i<5; i++) buttonOperate[i].setFocusPainted(false);
		buttonOperate[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"+";
				field.setText(tmp);
				expression=expression+"+";
				if(isMute==true) return;
				String path="/sounds/add.wav";
				playSound.run(path);
			}
		});
		buttonOperate[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"-";
				field.setText(tmp);
				expression=expression+"-";
				if(isMute==true) return;
				String path="/sounds/sub.wav";
				playSound.run(path);
			}
		});
		buttonOperate[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"×";
				field.setText(tmp);
				expression=expression+"*";
				if(isMute==true) return;
				String path="/sounds/multi.wav";
				playSound.run(path);
			}
		});
		buttonOperate[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"÷";
				field.setText(tmp);
				expression=expression+"/";
				if(isMute==true) return;
				String path="/sounds/divide.wav";
				playSound.run(path);
			}
		});
		buttonOperate[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result=resultDisplay.display(expression);
				field.setText(result);
				historyDisplay.display(expression, result);
				expression="";
				isNew=true;
				if(isMute==false) {
					String path="/sounds/equal.wav";
					playSound.run(path);
					if(!result.equals("Error") || !result.equals("NaN")) {
						Timer timer=new Timer(500, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								new Thread(()->{
									readResult.run(result);
								}).start();
							}
						});
						timer.setRepeats(false);
						timer.start();
					}
				}
			}
		});
		
		buttonLetter=new JButton[7];
		buttonLetter[0]=new JButton("sqrt");
		buttonLetter[1]=new JButton("sin");
		buttonLetter[2]=new JButton("cos");
		buttonLetter[3]=new JButton("tan");
		buttonLetter[4]=new JButton("sin⁻¹");
		buttonLetter[5]=new JButton("cos⁻¹");
		buttonLetter[6]=new JButton("tan⁻¹");
		buttonLetter[0].setBackground(Color.yellow);
		for(int i=1; i<7; i++) buttonLetter[i].setBackground(Color.orange);
		for(int i=0; i<7; i++) buttonLetter[i].setFont(Format.font_smallLetter);
		buttonLetter[0].addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				buttonLetter[0].setBackground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				buttonLetter[0].setBackground(Color.yellow);
			}
		});
		for(int i=1; i<7; i++) {
			int index=i;
			buttonLetter[i].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					buttonLetter[index].setBackground(Color.white);
				}
				public void mouseExited(MouseEvent e) {
					buttonLetter[index].setBackground(Color.orange);
				}
			});
		}
		for(int i=0; i<7; i++) buttonLetter[i].setFocusPainted(false);
		buttonLetter[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"sqrt";
				field.setText(tmp);
				expression=expression+"sqrt";
				if(isMute==true) return;
				String path="/sounds/sqrt.wav";
				playSound.run(path);
			}
		});
		buttonLetter[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"sin";
				field.setText(tmp);
				expression=expression+"sin";
				if(isMute==true) return;
				String path="/sounds/sin.wav";
				playSound.run(path);
			}
		});
		buttonLetter[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"cos";
				field.setText(tmp);
				expression=expression+"cos";
				if(isMute==true) return;
				String path="/sounds/cos.wav";
				playSound.run(path);
			}
		});
		buttonLetter[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"tan";
				field.setText(tmp);
				expression=expression+"tan";
				if(isMute==true) return;
				String path="/sounds/tan.wav";
				playSound.run(path);
			}
		});
		buttonLetter[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"arcsin";
				field.setText(tmp);
				expression=expression+"asin";
				if(isMute==true) return;
				String path="/sounds/arcsin.wav";
				playSound.run(path);
			}
		});
		buttonLetter[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"arccos";
				field.setText(tmp);
				expression=expression+"acos";
				if(isMute==true) return;
				String path="/sounds/arccos.wav";
				playSound.run(path);
			}
		});
		buttonLetter[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNew==true) {
					field.setText(null);
					isNew=false;
				}
				String tmp=field.getText()+"arctan";
				field.setText(tmp);
				expression=expression+"atan";
				if(isMute==true) return;
				String path="/sounds/arctan.wav";
				playSound.run(path);
			}
		});
		
		buttonClear=new JButton("AC");
		buttonClear.setBackground(Color.pink);
		buttonClear.setFont(Format.font_clear);
		buttonClear.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {   
                buttonClear.setBackground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
                buttonClear.setBackground(Color.pink);
			}
		});
		buttonClear.setFocusPainted(false);
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(null);
				expression="";
				if(isMute==true) return;
				String path="/sounds/clear.wav";
				playSound.run(path);
			}
		});
		
		button.setLayout(new GridLayout(6, 4));
		button.add(buttonNumber[1]);
		button.add(buttonNumber[2]);
		button.add(buttonNumber[3]);
		button.add(buttonClear);
		button.add(buttonNumber[4]);
		button.add(buttonNumber[5]);
		button.add(buttonNumber[6]);
		button.add(buttonOperate[3]);
		button.add(buttonNumber[7]);
		button.add(buttonNumber[8]);
		button.add(buttonNumber[9]);
		button.add(buttonOperate[2]);
		button.add(buttonLetter[0]);
		button.add(buttonNumber[0]);
		button.add(buttonNumber[10]);
		button.add(buttonOperate[1]);
		button.add(buttonLetter[1]);
		button.add(buttonLetter[2]);
		button.add(buttonLetter[3]);
		button.add(buttonOperate[0]);
		button.add(buttonLetter[4]);
		button.add(buttonLetter[5]);
		button.add(buttonLetter[6]);
		button.add(buttonOperate[4]);
		
		field.setPreferredSize(new Dimension(336, 80));
		field.setBorder(Format.border_display);
		field.setFont(Format.font_display);
		field.setBackground(Color.white);
		field.setHorizontalAlignment(JTextField.RIGHT);
		field.setEditable(false);
		display.add(field);
		
		Image img_history=Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/history.png"));
		Image img_transform=Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/transform.png"));
		Image img_sound=Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/sound.png"));
		Image img_mute=Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/mute.png"));
		img_history=img_history.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		img_transform=img_transform.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		img_sound=img_sound.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		img_mute=img_mute.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon icon_history=new ImageIcon(img_history);
		ImageIcon icon_transform=new ImageIcon(img_transform);
		ImageIcon icon_sound=new ImageIcon(img_sound);
		ImageIcon icon_mute=new ImageIcon(img_mute);
		
		History=new JLabel(icon_history);
		transform=new JLabel(icon_transform);
		Mute=new JLabel(icon_sound);
		
		History.setBorder(Format.border_label);
		transform.setBorder(Format.border_label);
		Mute.setBorder(Format.border_sound);
		
		History.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new historyGUI();
			}
			public void mouseEntered(MouseEvent e) {
				History.setBorder(Format.border_selectedLabel);
			}
			public void mouseExited(MouseEvent e) {
				History.setBorder(Format.border_label);
			}
		});
		
		transform.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new transformGUI();
			}
			public void mouseEntered(MouseEvent e) {
				transform.setBorder(Format.border_selectedLabel);
			}
			public void mouseExited(MouseEvent e) {
				transform.setBorder(Format.border_label);
			}
		});
		
		Mute.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(isMute==false) {
					Mute.setBorder(Format.border_mute);
					Mute.setIcon(icon_mute);
					isMute=true;
				}
				else if(isMute==true) {
					Mute.setBorder(Format.border_sound);
					Mute.setIcon(icon_sound);
					isMute=false;
				}
			}
		});
		
		function.setLayout(new FlowLayout());
		function.add(History);
		function.add(transform);
		function.add(Mute);
		function.add(new JLabel("                                                             "));
		
		frame.add(display);
		frame.add(function);
		frame.add(button);
		frame.setVisible(true);
	}
}

class historyGUI{
	JFrame frame;
	JTextArea area;
	
	historyGUI(){init();}
	
	void init() {
		frame=new JFrame();
		frame.setTitle("历史记录");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300,552);
		frame.setLayout(new FlowLayout());
		frame.setLocation(252, 150);
		frame.setResizable(false);
		
		area=new JTextArea(19, 14);
		area.setEditable(false);
		area.setFont(Format.font_histroy);
		area.setText(historyDisplay.display());
		
		frame.add(area);
		frame.setVisible(true);
	}
}

class transformGUI{
	JFrame frame;
	JTextField field1,field2,field3;
	JLabel label;
	JPanel panel1,panel2,panel3;
	
	transformGUI(){init();}
	
	void init() {
		frame=new JFrame();
		frame.setTitle("进制转换");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 315);
		frame.setLayout(new FlowLayout());
		frame.setLocation(890, 280);
		frame.setResizable(false);
		
		field1=new JTextField();
		field2=new JTextField();
		field3=new JTextField();
		
		field1.setPreferredSize(new Dimension(40, 30));
		field1.setBorder(Format.border_transformBin);
		field1.setFont(Format.font_transformBin);
		field1.setBackground(Color.white);
		field1.setText("16");
		
		field2.setPreferredSize(new Dimension(270, 100));
		field2.setBorder(Format.border_transformNum);
		field2.setFont(Format.font_transformNum);
		field2.setBackground(Color.white);
		field2.setText("0");
		
		field3.setPreferredSize(new Dimension(270, 100));
		field3.setBorder(Format.border_transformNum);
		field3.setFont(Format.font_transformNum);
		field3.setBackground(Color.white);
		field3.setEditable(false);
		
		label=new JLabel("进制");
		label.setFont(Format.font_chinese);
		
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		
		panel1.setLayout(new FlowLayout());
		panel1.add(new JLabel("                                                   "));
		panel1.add(field1);
		panel1.add(label);
		
		panel2.setLayout(new FlowLayout());
		panel2.add(field2);	
		
		panel3.setLayout(new FlowLayout());
		panel3.add(field3);
		
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		
		field1.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				setField3();
			}
			public void removeUpdate(DocumentEvent e) {
				setField3();
			}
			public void changedUpdate(DocumentEvent e) {
				setField3();
			}
			private void setField3() {
				try {
		            if(field2.getText().isEmpty() || field1.getText().isEmpty()) {
		                field3.setText("");
		                return;
		            }
		            double number=Double.parseDouble(field2.getText());
		            int binary=Integer.parseInt(field1.getText());
		            if(binary<2 || binary>36) {
		                field3.setText("Inlegal Binary");
		                return;
		            }
		            String result=Transform.transform(number, binary);
		            field3.setText(result);
		        }
				catch(Exception e) {
					field3.setText("Error");
		        }
            }
		});
		
		field2.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				setField3();
			}
			public void removeUpdate(DocumentEvent e) {
				setField3();
			}
			public void changedUpdate(DocumentEvent e) {
				setField3();
			}
			private void setField3() {
				try {
		            if(field2.getText().isEmpty() || field1.getText().isEmpty()) {
		                field3.setText("");
		                return;
		            }
		            double number=Double.parseDouble(field2.getText());
		            int binary=Integer.parseInt(field1.getText());
		            if(binary<2 || binary>36) {
		                field3.setText("Inlegal Binary");
		                return;
		            }
		            String result=Transform.transform(number, binary);
		            field3.setText(result);
		        }
				catch(Exception e) {
					field3.setText("Error");
		        }
            }
		});
		
		frame.setVisible(true);
	}
}