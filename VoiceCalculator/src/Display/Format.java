package Display;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Format {
	public static Font font_bigLetter=new Font("Calibri",Font.PLAIN, 30);
	public static Font font_smallLetter=new Font("Calibri",Font.PLAIN, 25);
	public static Font font_number=new Font("Verdana", Font.PLAIN, 40);
	public static Font font_clear=new Font("Calibri", Font.PLAIN, 35);
	public static Font font_display=new Font("Verdana", Font.PLAIN, 60);
	public static Font font_chinese=new Font("SimHei", Font.PLAIN, 20);
	public static Font font_transformNum=new Font("Verdana", Font.PLAIN, 25);
	public static Font font_histroy=new Font("Verdana", Font.PLAIN, 20);
	public static Font font_transformBin=new Font("Verdana", Font.PLAIN, 18);
	
	public static Border border_display=BorderFactory.createLineBorder(Color.black, 2);
	public static Border border_button=BorderFactory.createLineBorder(Color.lightGray, 1);
	public static Border border_label=BorderFactory.createLineBorder(Color.lightGray, 1);
	public static Border border_selectedLabel=BorderFactory.createLineBorder(Color.gray, 1);
	public static Border border_sound=BorderFactory.createLineBorder(Color.green, 1);
	public static Border border_mute=BorderFactory.createLineBorder(Color.red, 1);
	public static Border border_transformNum=BorderFactory.createLineBorder(Color.black, 2);
	public static Border border_transformBin=BorderFactory.createLineBorder(Color.black, 1);
}
