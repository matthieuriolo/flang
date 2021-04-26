package ch.ffhs.fac.flang.runtime.operations;

import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Operation;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class BiOperand implements Operation {
	private final Expression left;
	private final Expression right;
	private final Type type;

	public enum Type {
		OR,
		AND,
		PLUS,
		MINUS,
		ASTERISK,
		SLASH,
		EQUAL,
		NOT_EQUAL,
		LESS,
		LESS_EQUAL,
		GREATER,
		GREATER_EQUAL,
		;
	};

	public BiOperand(final Expression left, final Type type, final Expression right) {
		this.left = left;
		this.type = type;
		this.right = right;
	}

	@Override
	public Literal compute(Context ctx) {
		final var l = left.compute(ctx);
		final var r = right.compute(ctx);
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

}
