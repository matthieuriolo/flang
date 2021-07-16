package ch.ffhs.fac.flang.runtime.literals;

import java.math.RoundingMode;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LiteralBase;

public class String extends LiteralBase {
	public final static java.lang.String NAME = "String";
	private final java.lang.String string;
	public String() {
		this("");
	}
	
	public String(final java.lang.String string) {
		this.string = string;
	}
	
	@Override
	public Literal computePlus(final Literal right) {
		if (right instanceof String) {
			return new String(string + ((String) right).string);
		}

		return super.computePlus(right);
	}
	
	@Override
	public Literal computeAsterisk(final Literal right) {
		if (right instanceof Decimal) {
			final var count = ((Decimal) right).getValue().setScale(0, RoundingMode.DOWN).intValueExact();
			if(count < 0) {
				return Undefined.UNDEFINED;
			}
			return new String(string.repeat(count));
		}
		
		return super.computeAsterisk(right);
	}
	
	@Override
	public Literal computeEqual(final Literal right) {
		if (right instanceof String) {
			return Boolean.of(string.equals(((String) right).string));
		}

		return super.computeEqual(right);
	}
	
	@Override
	public Literal computeNotEqual(final Literal right) {
		if (right instanceof String) {
			return Boolean.of(!string.equals(((String) right).string));
		}

		return super.computeNotEqual(right);
	}
	
	@Override
	public java.lang.String toString() {
		return string;
	}
	
	@Override
	public java.lang.String toHumanReadableString() {
		return "\"" + string + "\"";
	}
	
	@Override
	public boolean toBoolean() {
		return !string.isBlank();
	}
	
	@Override
	public Literal toDecimal(final Context closure) {
		try {
			return new Decimal(string);
		}catch(Throwable e) {
			// if the conversion fails we silently catch the exception
			// and instead return undefined
			return Undefined.UNDEFINED;
		}
	}
	
	@Override
	public java.lang.String getTypeName() {
		return String.NAME;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteralString(this);
	}
}
