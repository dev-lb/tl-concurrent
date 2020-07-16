package dev.proj.springboot.spel;

public interface IExpression {
	Object calculate(Object data, String expression);
}
