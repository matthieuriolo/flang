package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.runtime.Literal;

public class Integer extends Number {
	private final java.lang.Integer integer;

	public Integer(final java.lang.Integer integer) {
		this.integer = integer;
	}
	
	public java.lang.Integer getValue() {
		return integer;
	}

	@Override
	public Literal operationPlus(final Literal right) {
		if (right instanceof Integer) {
			return new Integer(integer + ((Integer) right).integer);
		}

		return super.operationPlus(right);
	}

	@Override
	public Literal operationMinus(final Literal right) {
		if (right instanceof Integer) {
			return new Integer(integer - ((Integer) right).integer);
		}

		return super.operationMinus(right);
	}

	@Override
	public Literal operationAsterisk(final Literal right) {
		if (right instanceof Integer) {
			return new Integer(integer * ((Integer) right).integer);
		}

		return super.operationAsterisk(right);
	}

	// TODO: we maybe really need floats :/
	@Override
	public Literal operationSlash(final Literal right) {
		if (right instanceof Integer) {
			return new Integer(integer / ((Integer) right).integer);
		}

		return super.operationSlash(right);
	}

	@Override
	public Literal operationEqual(final Literal right) {
		if (right instanceof Integer) {
			return Boolean.of(integer == ((Integer) right).integer);
		}

		return super.operationEqual(right);
	}

	@Override
	public Literal operationNotEqual(final Literal right) {
		if (right instanceof Integer) {
			return Boolean.of(integer != ((Integer) right).integer);
		}

		return super.operationNotEqual(right);
	}

	@Override
	public Literal operationLess(final Literal right) {
		if (right instanceof Integer) {
			return Boolean.of(integer < ((Integer) right).integer);
		}

		return super.operationLess(right);
	}

	@Override
	public Literal operationLessEqual(final Literal right) {
		if (right instanceof Integer) {
			return Boolean.of(integer <= ((Integer) right).integer);
		}

		return super.operationLessEqual(right);
	}

	@Override
	public Literal operationGreater(final Literal right) {
		if (right instanceof Integer) {
			return Boolean.of(integer > ((Integer) right).integer);
		}

		return super.operationGreater(right);
	}

	@Override
	public Literal operationGreaterEqual(final Literal right) {
		if (right instanceof Integer) {
			return Boolean.of(integer >= ((Integer) right).integer);
		}

		return super.operationGreater(right);
	}

	@Override
	public Literal operationUnaryMinus() {
		return new Integer(-integer);
	}

	@Override
	public java.lang.String toString() {
		return java.lang.String.valueOf(integer);
	}
}
