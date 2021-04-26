package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.runtime.Literal;

public class Boolean implements Literal {
	public static final Boolean TRUE = new Boolean("true");
	public static final Boolean FALSE = new Boolean("false");
	
	private final java.lang.String name;
	private Boolean(final java.lang.String string) {
		this.name = string;
	}
	
	@Override
	public java.lang.String toString() {
		return name;
	}
}
