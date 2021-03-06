package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;

/**
 * Instruction for an if statement
 * @author matthieuriolo
 *
 */
public class If extends LocatedInTextBase implements Instruction {
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
	
	/**
	 * Getter for the condition
	 * @return an {@link Expression}
	 */
	public Expression getCondition() {
		return condition;
	}

	/**
	 * Getter for the containing instructions inside the if block
	 * @return a list of {@link Instruction}
	 */
	public List<Instruction> getInstructions() {
		return instructions;
	}

	/**
	 * Getter for the containing instructions inside the else block
	 * @return a list of {@link Instruction}
	 */
	public List<Instruction> getElseInstructions() {
		return elseInstructions;
	}
	
	@Override
	public Literal execute(Context closure) throws Throwable {
		final var cond = condition.compute(closure);
		final var instrs = cond.toBoolean() ? instructions : elseInstructions;
		final var ctx = new Context(closure);
		return ctx.execute(instrs);
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitInstructionIf(this);
	}
}
