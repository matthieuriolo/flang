package ch.ffhs.fac.flang.runtime.expressions.operations;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Visitor;
import ch.ffhs.fac.flang.runtime.expressions.Operation;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class BiOperand implements Operation {
	private final Expression left;
	private final Expression right;
	private final Type type;

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
		
		public java.lang.String getName() {
			return name;
		}
	};

	public BiOperand(final Expression left, final Type type, final Expression right) {
		this.left = left;
		this.type = type;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	public Type getType() {
		return type;
	}

	@Override
	public Literal compute(final Closure closure) throws Throwable {
		final var l = left.compute(closure);
		final var r = right.compute(closure);
		switch (type) {
		case OR:
			return l.operationOr(r);
		case AND:
			return l.operationAnd(r);
		case PLUS:
			return l.operationPlus(r);
		case MINUS:
			return l.operationMinus(r);
		case SLASH:
			return l.operationSlash(r);
		case ASTERISK:
			return l.operationAsterisk(r);
		case EQUAL:
			return l.operationEqual(r);
		case NOT_EQUAL:
			return l.operationNotEqual(r);
		case GREATER:
			return l.operationGreater(r);
		case GREATER_EQUAL:
			return l.operationGreaterEqual(r);
		case LESS:
			return l.operationLess(r);
		case LESS_EQUAL:
			return l.operationLessEqual(r);
		default:
			return Undefined.UNDEFINED;
		}
	}


	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitExpressionBiOperand(this);
	}
}
