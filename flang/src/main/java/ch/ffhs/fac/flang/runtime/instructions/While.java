package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;

/**
 * Instruction for a while-loop
 * @author matthieuriolo
 *
 */
public class While extends LocatedInTextBase implements Instruction {
	private final Expression condition;
	private final List<Instruction> instructions;

	public While(final Expression condition, final List<Instruction> instructions) {
		this.condition = condition;
		this.instructions = instructions;
	}
	/**
	 * Getter for the condition
	 * @return an {@link Expression}
	 */
	public Expression getCondition() {
		return condition;
	}

	/**
	 * Getter for the containing instructions inside the loop
	 * @return a list of {@link Instruction}
	 */
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	@Override
	public Literal execute(Context closure) throws Throwable {
		while(condition.compute(closure).toBoolean()) {
			final var returnLiteral = new Context(closure).execute(instructions);
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
