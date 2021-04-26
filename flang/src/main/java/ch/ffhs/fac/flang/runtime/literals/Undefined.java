package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.runtime.Literal;

public class Undefined implements Literal {
	public static final Undefined UNDEFINED = new Undefined();
	
	@Override
	public java.lang.String toString() {
		return "Undefined";
	}
}
