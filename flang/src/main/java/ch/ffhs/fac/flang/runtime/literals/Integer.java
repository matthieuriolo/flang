package ch.ffhs.fac.flang.runtime.literals;

public class Integer extends Number {
	private final java.lang.Integer integer;
	public Integer(final java.lang.Integer integer) {
		this.integer = integer;
	}
	
	@Override
	public java.lang.String toString() {
		return java.lang.String.valueOf(integer);
	}
}
