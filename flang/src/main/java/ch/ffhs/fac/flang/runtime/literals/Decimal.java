package ch.ffhs.fac.flang.runtime.literals;

import java.math.BigDecimal;
import java.math.MathContext;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LiteralBase;

/**
 * Literal for the type "Decimal" (aka int and double)
 * @author matthieuriolo
 *
 */
public class Decimal extends LiteralBase {
	public final static java.lang.String NAME = "Decimal";
	public final static Decimal ZERO = new Decimal("0");
	public final static Decimal ONE = new Decimal("1");
	
	private BigDecimal value;
	
	public Decimal(final BigDecimal value) {
		this.value = value;
	}
	
	public Decimal(final java.lang.String value) {
		this(new BigDecimal(value));
	}
	
	public Decimal(final boolean value) {
		this(value ? BigDecimal.ONE : BigDecimal.ZERO);
	}
	
	public Decimal(final int value) {
		this(new BigDecimal(value));
	}
	
	public Decimal(final double value) {
		this(new BigDecimal(value));
	}
	
	/**
	 * Getter for the internal {@link BigDecimal} value
	 * @return a {@link BigDecimal}
	 */
	public BigDecimal getValue() {
		return value;
	}
	
	@Override
	public Literal computePlus(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.add(((Decimal) right).value));
		}

		return super.computePlus(right);
	}

	@Override
	public Literal computeMinus(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.subtract(((Decimal) right).value));
		}

		return super.computeMinus(right);
	}

	@Override
	public Literal computeAsterisk(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.multiply(((Decimal) right).value));
		}

		return super.computeAsterisk(right);
	}

	@Override
	public Literal computeSlash(final Literal right) {
		if (right instanceof Decimal) {
			final var val = ((Decimal)right).value;
			if (val.compareTo(BigDecimal.ZERO) == 0) {
				return Undefined.UNDEFINED;
			}

			return new Decimal(value.divide(val, MathContext.DECIMAL128));
		}

		return super.computeSlash(right);
	}

	@Override
	public Literal computeEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) == 0);
		}

		return super.computeEqual(right);
	}

	@Override
	public Literal computeNotEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) != 0);
		}

		return super.computeNotEqual(right);
	}

	@Override
	public Literal computeLess(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) < 0);
		}

		return super.computeLess(right);
	}

	@Override
	public Literal computeLessEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) <= 0);
		}

		return super.computeLessEqual(right);
	}

	@Override
	public Literal computeGreater(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) > 0);
		}

		return super.computeGreater(right);
	}

	@Override
	public Literal computeGreaterEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) >= 0);
		}

		return super.computeGreater(right);
	}

	@Override
	public Literal computeUnaryMinus() {
		return new Decimal(value.negate());
	}

	@Override
	public java.lang.String toString() {
		return value.toString();
	}
	
	@Override
	public boolean toBoolean() {
		return value.compareTo(BigDecimal.ZERO) != 0;
	}
	
	@Override
	public Literal toDecimal(final Context closure) {
		return this;
	}
	
	@Override
	public java.lang.String getTypeName() {
		return Decimal.NAME;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteralDecimal(this);
	}
}
