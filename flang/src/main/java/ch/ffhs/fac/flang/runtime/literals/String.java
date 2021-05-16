package ch.ffhs.fac.flang.runtime.literals;

import java.math.BigDecimal;

import ch.ffhs.fac.flang.runtime.Closure;
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
		if (right instanceof Decimal) {
			return new String(string.repeat(((Decimal) right).getValue().intValueExact()));
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
	public java.lang.String toString(final Closure closure) {
		return string;
	}
	
	@Override
	public java.lang.String toHumanReadableString(final Closure closure) {
		return "\"" + string + "\"";
	}
	
	@Override
	public boolean toBoolean(final Closure closure) {
		return !string.isBlank();
	}
	
	@Override
	public Literal toDecimal(Closure closure) {
		try {
			return new Decimal(string);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		return Undefined.UNDEFINED;
	}
}
