package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.runtime.Literal;

public class String implements Literal {
	private final java.lang.String string;
	public String() {
		this("");
	}
	
	public String(final java.lang.String string) {
		this.string = string;
	}
}
