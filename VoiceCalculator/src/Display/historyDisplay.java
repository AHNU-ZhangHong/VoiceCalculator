package Display;

public class historyDisplay {
	private static String saveData="";
	public static String display(String expression, String result) {
		saveData=saveData+expression;
		saveData=saveData+"=";
		saveData=saveData+result;
		saveData=saveData+"\n";
		return saveData;
	}
	public static String display() {
		return saveData;
	}
}
