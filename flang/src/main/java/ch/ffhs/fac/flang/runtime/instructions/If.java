package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Visitable;
import ch.ffhs.fac.flang.runtime.Visitor;

public class If implements Instruction {
	private final Expression condition;
	private final List<Instruction> instructions;
	private final List<Instruction> elseInstructions;

	public If(final Expression condition, final List<Instruction> instructions) {
		this(condition, instructions, List.of());
	}
	
	public If(final Expression condition, final List<Instruction> instructions, final List<Instruction> elseInstructions) {
		this.condition = condition;
		this.instructions = instructions;
		this.elseInstructions = elseInstructions;
	}
	
	public Expression getCondition() {
		return condition;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public List<Instruction> getElseInstructions() {
		return elseInstructions;
	}
	
	@Override
	public Literal execute(Closure closure) throws Throwable {
		final var cond = condition.compute(closure);
		final var instrs = cond.toBoolean() ? instructions : elseInstructions;
		final var block = new Closure(closure, instrs);
		return block.execute();
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitInstructionIf(this);
	}
}
