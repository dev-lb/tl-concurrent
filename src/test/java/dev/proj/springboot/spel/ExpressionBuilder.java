package dev.proj.springboot.spel;

public class ExpressionBuilder {
	private static IExpression defaultExpression=new SpelExpression(); 
	private static ExpressionBuilder instance;
	public static ExpressionBuilder getInstance()
	{
		return instance;
	}
	private ExpressionBuilder()
	{
	}
	
	public static IExpression   getDefault()
	{
		return defaultExpression;
	}
	
}
