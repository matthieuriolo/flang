package ch.ffhs.fac.flang.runtime.expressions;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

/**
 * Expression for binary operation (as example -1)
 * @author matthieuriolo
 *
 */
public class UnaryOperation extends LocatedInTextBase implements Expression {
	private final Expression operand;
	private final Type type;
	
	/**
	 * Enum containing the supported operators
	 * @author matthieuriolo
	 *
	 */
	public enum Type {
		MINUS("MINUS");
		
		private final java.lang.String name;
		
		private Type(final java.lang.String name) {
			this.name = name;
		}
		
		/**
		 * Getter for the name of the type
		 * @return the name of the type
		 */
		public java.lang.String getName() {
			return name;
		}
	}

	public UnaryOperation(final Expression operand, final Type type) {
		this.operand = operand;
		this.type = type;
	}
	
	/**
	 * Getter for the operand
	 * @return an {@link Expression}
	 */
	public Expression getOperand() {
		return operand;
	}
	
	/**
	 * Getter for the operand
	 * @return a {@link Type}
	 */
	public Type getType() {
		return type;
	}

	@Override
	public Literal compute(final Context closure) throws Throwable {
		final var l = operand.compute(closure);
		switch(type) {
		case MINUS:
			return l.computeUnaryMinus();
			default:
				return Undefined.UNDEFINED;
		}
	}
	

	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitExpressionUnaryOperand(this);
	}
}
