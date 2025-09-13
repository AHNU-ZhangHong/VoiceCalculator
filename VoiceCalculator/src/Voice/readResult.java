package Voice;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class readResult {
	private static final String[] CHINESE_DIGITS={"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] CHINESE_UNITS={"", "十", "百", "千"};
    private static final String[] LARGE_UNITS={"", "万", "亿"};
    
	public static void run(String result) {
		String text=NumberToText(result);
		for(char tmp:text.toCharArray()) {
			if(tmp=='负') read("/sounds/fu.wav");
			else if(tmp=='零') read("/sounds/0.wav");
			else if(tmp=='一') read("/sounds/1.wav");
			else if(tmp=='二') read("/sounds/2.wav");
			else if(tmp=='三') read("/sounds/3.wav");
			else if(tmp=='四') read("/sounds/4.wav");
			else if(tmp=='五') read("/sounds/5.wav");
			else if(tmp=='六') read("/sounds/6.wav");
			else if(tmp=='七') read("/sounds/7.wav");
			else if(tmp=='八') read("/sounds/8.wav");
			else if(tmp=='九') read("/sounds/9.wav");
			else if(tmp=='十') read("/sounds/ten.wav");
			else if(tmp=='百') read("/sounds/bai.wav");
			else if(tmp=='千') read("/sounds/qian.wav");
			else if(tmp=='万') read("/sounds/wan.wav");
			else if(tmp=='亿') read("/sounds/yi.wav");
			else if(tmp=='点') read("/sounds/point.wav");
		}
	}
	
	private static void read(String path) {
		try {
			URL url=readResult.class.getResource(path);
			AudioInputStream stream=AudioSystem.getAudioInputStream(url);
			Clip clip=AudioSystem.getClip();
			clip.open(stream);
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);
		}
		catch(Exception e) {}
	}
	
	private static String NumberToText(String number) {
		boolean isNegative=number.startsWith("-");
        if(isNegative) {
            number=number.substring(1);
        }
        String result;
        if(number.contains(".")) {
            String[] parts=number.split("\\.");
            String integerPart=parts[0];
            String decimalPart=parts.length>1?parts[1]:"";
            result=convertIntegerToChinese(Long.parseLong(integerPart))+"点"+convertDecimalToChinese(decimalPart);
        }
        else {
            result=convertIntegerToChinese(Long.parseLong(number));
        }
        return isNegative?"负"+result:result;
	}
	
	private static String convertIntegerToChinese(long number) {
		if (number==0) {
            return CHINESE_DIGITS[0];
        }
        StringBuilder chineseNumber=new StringBuilder();
        String numStr=String.valueOf(number);
        int length=numStr.length();
        boolean hasZero=false;
        for (int i=0; i<length; i++) {
            int digit=numStr.charAt(i) - '0';
            int position=(length-i-1)%4;
            int segmentIndex=(length-i-1)/4;
            if(digit==0) {
                hasZero=true;
            }
            else {
                if(hasZero) {
                    chineseNumber.append(CHINESE_DIGITS[0]);
                    hasZero=false;
                }
                chineseNumber.append(CHINESE_DIGITS[digit]);
                chineseNumber.append(CHINESE_UNITS[position]);
            }
            if(position==0 && segmentIndex>0) {
                chineseNumber.append(LARGE_UNITS[segmentIndex]);
            }
        }
        String result=chineseNumber.toString();
        if(result.startsWith("一十")) {
            result=result.substring(1);
        }
        while (result.endsWith("零")) {
            result=result.substring(0, result.length()-1);
        }
        return result;
	}
	
	private static String convertDecimalToChinese(String decimalPart) {
        StringBuilder chineseDecimal=new StringBuilder();
        for(char c:decimalPart.toCharArray()) {
            int digit=c-'0';
            chineseDecimal.append(CHINESE_DIGITS[digit]);
        }
        return chineseDecimal.toString();
    }
}
