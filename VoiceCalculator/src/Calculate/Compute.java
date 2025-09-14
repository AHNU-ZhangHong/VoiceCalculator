package Calculate;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Compute {
	public static String compute(String expression) {
		Expression express=new ExpressionBuilder(expression).build();
		double result=express.evaluate();
		String answer=String.format("%.2f", result);
                                System.out.println("zh 是人机");
		return answer;
	}
}
