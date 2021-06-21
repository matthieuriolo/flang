package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Visitable;
import ch.ffhs.fac.flang.runtime.Visitor;

public class While implements Instruction {
	private final Expression condition;
	private final List<Instruction> instructions;

	public While(final Expression condition, final List<Instruction> instructions) {
		this.condition = condition;
		this.instructions = instructions;
	}
	
	public Expression getCondition() {
		return condition;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	@Override
	public Literal execute(Context closure) throws Throwable {
		while(condition.compute(closure).toBoolean()) {
			final var returnLiteral = new Context(closure, instructions).execute();
			if(returnLiteral != null) {
				return returnLiteral;
			}
		}
		
		return null;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitInstructionWhile(this);
	}
}
