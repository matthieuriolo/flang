package ch.ffhs.fac.flang.runtime.literals;

import java.math.BigDecimal;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitable;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;

public class Decimal extends LiteralBase {
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
	
	public BigDecimal getValue() {
		return value;
	}
	
	@Override
	public Literal operationPlus(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.add(((Decimal) right).value));
		}

		return super.operationPlus(right);
	}

	@Override
	public Literal operationMinus(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.subtract(((Decimal) right).value));
		}

		return super.operationMinus(right);
	}

	@Override
	public Literal operationAsterisk(final Literal right) {
		if (right instanceof Decimal) {
			return new Decimal(value.multiply(((Decimal) right).value));
		}

		return super.operationAsterisk(right);
	}

	@Override
	public Literal operationSlash(final Literal right) {
		if (right instanceof Decimal) {
			// TODO we should check if it is a division by zero
			return new Decimal(value.multiply(((Decimal) right).value));
		}

		return super.operationSlash(right);
	}

	@Override
	public Literal operationEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) == 0);
		}

		return super.operationEqual(right);
	}

	@Override
	public Literal operationNotEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) != 0);
		}

		return super.operationNotEqual(right);
	}

	@Override
	public Literal operationLess(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) < 0);
		}

		return super.operationLess(right);
	}

	@Override
	public Literal operationLessEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) <= 0);
		}

		return super.operationLessEqual(right);
	}

	@Override
	public Literal operationGreater(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) > 0);
		}

		return super.operationGreater(right);
	}

	@Override
	public Literal operationGreaterEqual(final Literal right) {
		if (right instanceof Decimal) {
			return Boolean.of(value.compareTo(((Decimal) right).value) >= 0);
		}

		return super.operationGreater(right);
	}

	@Override
	public Literal operationUnaryMinus() {
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
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteralDecimal(this);
	}
}
