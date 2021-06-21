package ch.ffhs.fac.flang.runtime.instructions;

import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Visitor;
import ch.ffhs.fac.flang.runtime.literals.Identifier;

public class Assignment implements Instruction {
	private final Identifier identifier;
	private final Expression expression;
	
	public Assignment(final Identifier identifier, final Expression expression) {
		this.identifier = identifier;
		this.expression = expression;
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	@Override
	public Literal execute(Context closure) throws Throwable {
		final var value = expression.compute(closure);
		closure.setValue(identifier, value);
		return null;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitInstructionAssignment(this);
	}
}
