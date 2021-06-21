package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitable;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;

public class Boolean implements Literal {
	public static final Boolean TRUE = new Boolean("TRUE");
	public static final Boolean FALSE = new Boolean("FALSE");
	
	private final java.lang.String name;
	private Boolean(final java.lang.String string) {
		this.name = string;
	}
	
	public static Boolean of(boolean value) {
		return value ? Boolean.TRUE : Boolean.FALSE;
	}
	
	@Override
	public java.lang.String toString() {
		return name;
	}

	@Override
	public boolean toBoolean() {
		return this == Boolean.TRUE;
	}
	
	@Override
	public Literal operationEqual(final Literal right) {
		return Boolean.of(this == right);
	}
	
	@Override
	public Literal operationNotEqual(final Literal right) {
		return Boolean.of(this != right);
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteralBoolean(this);
	}
}
