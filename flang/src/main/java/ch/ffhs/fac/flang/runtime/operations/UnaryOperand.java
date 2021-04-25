package ch.ffhs.fac.flang.runtime.operations;

import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Operation;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class UnaryOperand implements Operation {
	private final Expression operand;
	private final Type type;
	
	public enum Type {
		MINUS;
	}

	public UnaryOperand(final Expression operand, final Type type) {
		this.operand = operand;
		this.type = type;
	}
	
	@Override
	public Literal compute(Context ctx) {
		final var l = operand.compute(ctx);
		switch(type) {
		case MINUS:
			return l.operationUnaryMinus();
			default:
				return Undefined.UNDEFINED;
		}
	}
}
