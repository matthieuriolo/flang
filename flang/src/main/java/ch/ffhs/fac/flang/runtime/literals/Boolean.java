package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Literal;

public class Boolean implements Literal {
	public static final Boolean TRUE = new Boolean("true");
	public static final Boolean FALSE = new Boolean("false");
	
	private final java.lang.String name;
	private Boolean(final java.lang.String string) {
		this.name = string;
	}
	
	static Boolean of(boolean value) {
		return value ? Boolean.TRUE : Boolean.FALSE;
	}
	
	@Override
	public java.lang.String toString() {
		return name;
	}

	@Override
	public boolean toBoolean(Closure closure) {
		return this == Boolean.TRUE;
	}
}
