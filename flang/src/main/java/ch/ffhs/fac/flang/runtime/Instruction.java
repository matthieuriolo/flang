package ch.ffhs.fac.flang.runtime;

public interface Instruction extends Visitable {
	public Literal execute(final Context closure) throws Throwable;
}
