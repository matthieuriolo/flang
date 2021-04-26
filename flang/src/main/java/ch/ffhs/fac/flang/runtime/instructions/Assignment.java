package ch.ffhs.fac.flang.runtime.instructions;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.literals.Identifier;

public class Assignment implements Instruction {
	private final Identifier identifier;
	private final Expression expression;
	
	public Assignment(final Identifier identifier, final Expression expression) {
		this.identifier = identifier;
		this.expression = expression;
	}
	
	@Override
	public Literal execute(Closure closure) {
		final var value = expression.compute(closure);
		closure.setValue(identifier, value);
		return value;
	}
}
