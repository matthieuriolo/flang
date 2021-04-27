package ch.ffhs.fac.flang.runtime.expressions;

import java.util.List;
import java.util.Objects;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Routine;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class FunctionCall extends Routine implements Expression {
	public FunctionCall(final Expression subject) {
		super(subject);
	}
	
	public FunctionCall(final Expression subject, final List<Expression> arguments) {
		super(subject, arguments);
	}
	
	@Override
	public Literal compute(Closure closure) throws Throwable {
		return Objects.requireNonNullElse(perform(closure), Undefined.UNDEFINED);
	}
}
