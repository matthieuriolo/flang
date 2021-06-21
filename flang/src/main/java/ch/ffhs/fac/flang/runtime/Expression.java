package ch.ffhs.fac.flang.runtime;

public interface Expression extends Visitable {
	public Literal compute(final Context closure) throws Throwable;
}
