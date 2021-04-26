package ch.ffhs.fac.flang.runtime;

public interface Instruction {
	public Literal execute(final Closure closure);
}
