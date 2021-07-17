package ch.ffhs.fac.flang.runtime.expressions;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

/**
 * Expression for binary operation (as example 1+1)
 * @author matthieuriolo
 *
 */
public class BinaryOperation extends LocatedInTextBase implements Expression {
	private final Expression left;
	private final Expression right;
	private final Type type;
	
	/**
	 * Enum containing the supported operators
	 * @author matthieuriolo
	 *
	 */
	public enum Type {
		OR("OR"),
		AND("AND"),
		PLUS("PLUS"),
		MINUS("MINUS"),
		ASTERISK("ASTERISK"),
		SLASH("SLASH"),
		EQUAL("EQUAL"),
		NOT_EQUAL("NOT_EQUAL"),
		LESS("LESS"),
		LESS_EQUAL("LESS_EQUAL"),
		GREATER("GREATER"),
		GREATER_EQUAL("GREATER_EQUAL"),
		;
		
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
	};

	public BinaryOperation(final Expression left, final Type type, final Expression right) {
		this.left = left;
		this.type = type;
		this.right = right;
	}

	/**
	 * Getter for the left handed operand
	 * @return an {@link Expression}
	 */
	public Expression getLeft() {
		return left;
	}

	/**
	 * Getter for the right handed operand
	 * @return an {@link Expression}
	 */
	public Expression getRight() {
		return right;
	}

	/**
	 * Getter for the operator
	 * @return a {@link Type}
	 */
	public Type getType() {
		return type;
	}

	@Override
	public Literal compute(final Context closure) throws Throwable {
		final var l = left.compute(closure);
		final var r = right.compute(closure);
		switch (type) {
		case OR:
			return l.computeOr(r);
		case AND:
			return l.computeAnd(r);
		case PLUS:
			return l.computePlus(r);
		case MINUS:
			return l.computeMinus(r);
		case SLASH:
			return l.computeSlash(r);
		case ASTERISK:
			return l.computeAsterisk(r);
		case EQUAL:
			return l.computeEqual(r);
		case NOT_EQUAL:
			return l.computeNotEqual(r);
		case GREATER:
			return l.computeGreater(r);
		case GREATER_EQUAL:
			return l.computeGreaterEqual(r);
		case LESS:
			return l.computeLess(r);
		case LESS_EQUAL:
			return l.computeLessEqual(r);
		default:
			return Undefined.UNDEFINED;
		}
	}


	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitExpressionBiOperand(this);
	}
}
