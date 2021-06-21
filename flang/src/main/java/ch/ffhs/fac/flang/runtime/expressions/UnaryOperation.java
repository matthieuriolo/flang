package ch.ffhs.fac.flang.runtime.expressions;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class UnaryOperation implements Expression {
	private final Expression operand;
	private final Type type;
	
	public enum Type {
		MINUS("MINUS");
		
		private final java.lang.String name;
		
		private Type(final java.lang.String name) {
			this.name = name;
		}
		
		public java.lang.String getName() {
			return name;
		}
	}

	public UnaryOperation(final Expression operand, final Type type) {
		this.operand = operand;
		this.type = type;
	}
	
	public Expression getOperand() {
		return operand;
	}

	public Type getType() {
		return type;
	}

	@Override
	public Literal compute(final Context closure) throws Throwable {
		final var l = operand.compute(closure);
		switch(type) {
		case MINUS:
			return l.operationUnaryMinus();
			default:
				return Undefined.UNDEFINED;
		}
	}
	

	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitExpressionUnaryOperand(this);
	}
}
