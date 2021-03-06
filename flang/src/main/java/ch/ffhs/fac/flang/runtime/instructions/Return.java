package ch.ffhs.fac.flang.runtime.instructions;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

/**
 * Instruction for a return statement
 * @author matthieuriolo
 *
 */
public class Return extends LocatedInTextBase implements Instruction {
	private final Expression expression;
	
	public Return() {
		this.expression = Undefined.UNDEFINED;
	}
	
	public Return(final Expression expression) {
		this.expression = expression;
	}
	
	/**
	 * Getter of the expression
	 * @return a {@link Expression}
	 */
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
