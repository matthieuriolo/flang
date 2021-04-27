package ch.ffhs.fac.flang.runtime.literals;

import java.math.BigDecimal;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Literal;

public class Decimal implements Literal {
	private BigDecimal value;
	
	public Decimal(final BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	@Override
	public Literal operationPlus(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.add(((Decimal) right).value));
		}

		return Literal.super.operationPlus(right);
	}

	@Override
	public Literal operationMinus(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.subtract(((Decimal) right).value));
		}

		return Literal.super.operationMinus(right);
	}

	@Override
	public Literal operationAsterisk(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.multiply(((Decimal) right).value));
		}

		return Literal.super.operationAsterisk(right);
	}

	@Override
	public Literal operationSlash(final Literal right) {
		if (right instanceof Decimal) {
			// TODO we should check if it is a division by zero
			return new Decimal(value.multiply(((Decimal) right).value));
		}

		return Literal.super.operationSlash(right);
	}

	@Override
	public Literal operationEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) == 0);
		}

		return Literal.super.operationEqual(right);
	}

	@Override
	public Literal operationNotEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) != 0);
		}

		return Literal.super.operationNotEqual(right);
	}

	@Override
	public Literal operationLess(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) < 0);
		}

		return Literal.super.operationLess(right);
	}

	@Override
	public Literal operationLessEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) <= 0);
		}

		return Literal.super.operationLessEqual(right);
	}

	@Override
	public Literal operationGreater(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) > 0);
		}

		return Literal.super.operationGreater(right);
	}

	@Override
	public Literal operationGreaterEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) >= 0);
		}

		return Literal.super.operationGreater(right);
	}

	@Override
	public Literal operationUnaryMinus() {
		return new Decimal(value.negate());
	}

	@Override
	public java.lang.String toString(final Closure closure) {
		return value.toString();
	}
	
	@Override
	public boolean toBoolean(final Closure closure) {
		return value.compareTo(BigDecimal.ZERO) != 0;
	}
}