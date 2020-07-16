package dev.proj.springboot.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelExpression implements IExpression {

	public Object calculate(Object map, String expression) {
		Object replaceValue = null;
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("map", map);
		if (expression != null) {
			replaceValue = parser.parseExpression(expression, new TemplateParserContext()).getValue(ctx);
		}
		return replaceValue;
	}

}
