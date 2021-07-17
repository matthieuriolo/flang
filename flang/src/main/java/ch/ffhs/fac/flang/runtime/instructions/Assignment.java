package ch.ffhs.fac.flang.runtime.instructions;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;
import ch.ffhs.fac.flang.runtime.expressions.Identifier;
/**
 * Instruction for assigning a value to an identifier
 * @author matthieuriolo
 *
 */
public class Assignment extends LocatedInTextBase implements Instruction {
	private final Identifier identifier;
	private final Expression expression;
	
	public Assignment(final Identifier identifier, final Expression expression) {
		this.identifier = identifier;
		this.expression = expression;
	}
	
	/**
	 * Getter of the identifier
	 * @return a {@link Identifier}
	 */
	public Identifier getIdentifier() {
		return identifier;
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
		final var value = expression.compute(closure);
		closure.setValue(identifier, value);
		return null;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitInstructionAssignment(this);
	}
}
