package Display;

import Calculate.Compute;

public class resultDisplay {
	public static String display(String expression) {
		String result="Error";
		try {
			result=Compute.compute(expression);
		}
		catch(Exception e) {}
		return result;
	}
}
