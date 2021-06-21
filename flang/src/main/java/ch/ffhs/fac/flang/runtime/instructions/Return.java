package ch.ffhs.fac.flang.runtime.instructions;

import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Visitable;
import ch.ffhs.fac.flang.runtime.Visitor;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class Return implements Instruction {
	private final Expression expression;
	
	public Return() {
		this.expression = Undefined.UNDEFINED;
	}
	
	public Return(final Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	@Override
	public Literal execute(Context closure) throws Throwable {
		return expression.compute(closure);
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitInstructionReturn(this);
	}
}
