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
	
	@Override
	public Literal operationPlus(final Literal right) {
		if (right instanceof String) {
			return new String(string + ((String) right).string);
		}

		return Literal.super.operationPlus(right);
	}
	
	@Override
	public Literal operationAsterisk(final Literal right) {
		if (right instanceof Integer) {
			return new String(string.repeat(((Integer) right).getValue()));
		}

		return Literal.super.operationAsterisk(right);
	}
	
	@Override
	public Literal operationEqual(final Literal right) {
		if (right instanceof String) {
			return Boolean.of(string.equals(((String) right).string));
		}

		return Literal.super.operationEqual(right);
	}
	
	@Override
	public Literal operationNotEqual(final Literal right) {
		if (right instanceof String) {
			return Boolean.of(!string.equals(((String) right).string));
		}

		return Literal.super.operationNotEqual(right);
	}
	
	@Override
	public java.lang.String toString() {
		return string;
	}
}
