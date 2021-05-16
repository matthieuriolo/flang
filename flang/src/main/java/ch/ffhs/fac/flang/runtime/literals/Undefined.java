package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Literal;

public class Undefined implements Literal {
	public static final Undefined UNDEFINED = new Undefined();
	
	@Override
	public java.lang.String toString(final Closure closure) {
		return "UNDEFINED";
	}

	@Override
	public boolean toBoolean(final Closure closure) {
		return false;
	}
	
	@Override
	public Literal toDecimal(final Closure closure) {
		return this;
	}
}
