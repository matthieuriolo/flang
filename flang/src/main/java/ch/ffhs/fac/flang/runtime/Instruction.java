package ch.ffhs.fac.flang.runtime;

public interface Instruction {
	public void execute(final Closure closure);
}
