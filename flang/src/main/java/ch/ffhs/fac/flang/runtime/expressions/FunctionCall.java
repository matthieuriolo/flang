package ch.ffhs.fac.flang.runtime.expressions;

import java.util.LinkedList;
import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Literal;

public class FunctionCall implements Expression {
	private final Expression subject;
	private final List<Expression> arguments;
	
	public FunctionCall(final Expression subject, final List<Expression> arguments) {
		this.subject = subject;
		this.arguments = arguments;
	}
	
	@Override
	public Literal compute(Closure closure) {
		final var literal = subject.compute(closure);
		final List<Literal> resolvedArguments = new LinkedList<>();
		
		for(final var expr : arguments) {
			resolvedArguments.add(expr.compute(closure));
		}
		
		return literal.functionalCall(closure, resolvedArguments);
	}
}
