package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Literal;

public class Undefined implements Literal {
	public static final Undefined UNDEFINED = new Undefined();
	
	@Override
	public java.lang.String toString() {
		return "UNDEFINED";
	}

	@Override
	public boolean toBoolean() {
		return false;
	}
	
	@Override
	public Literal toDecimal(final Closure closure) {
		return this;
	}
	
	@Override
	public Literal operationEqual(final Literal right) {
		return Boolean.of(this == right);
	}
	
	@Override
	public Literal operationNotEqual(final Literal right) {
		return Boolean.of(this != right);
	}
}
