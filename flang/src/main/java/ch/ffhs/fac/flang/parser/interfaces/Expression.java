package ch.ffhs.fac.flang.parser.interfaces;

import ch.ffhs.fac.flang.runtime.Context;

public interface Expression extends Visitable {
	public Literal compute(final Context closure) throws Throwable;
}
