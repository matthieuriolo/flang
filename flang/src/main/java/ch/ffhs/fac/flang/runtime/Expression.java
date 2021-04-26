package ch.ffhs.fac.flang.runtime;

public interface Expression {
	public Literal compute(final Closure closure);
}
