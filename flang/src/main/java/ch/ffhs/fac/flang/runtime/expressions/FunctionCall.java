package ch.ffhs.fac.flang.runtime.expressions;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Routine;
import ch.ffhs.fac.flang.runtime.literals.Identifier;

public class FunctionCall extends Routine implements Expression {
	public FunctionCall(final Identifier identifier, final List<Expression> arguments) {
		super(identifier, arguments);
	}
	
	public FunctionCall(final Expression subject, final List<Expression> arguments) {
		super(subject, arguments);
	}
	
	@Override
	public Literal compute(Closure closure) throws Throwable {
		return perform(closure);
	}
}
