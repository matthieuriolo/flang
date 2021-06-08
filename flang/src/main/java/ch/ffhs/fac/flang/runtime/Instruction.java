package ch.ffhs.fac.flang.runtime;

public interface Instruction extends Visitable {
	public Literal execute(final Closure closure) throws Throwable;
}
