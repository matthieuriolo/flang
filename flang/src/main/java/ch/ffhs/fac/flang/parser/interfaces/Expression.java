package ch.ffhs.fac.flang.parser.interfaces;

import ch.ffhs.fac.flang.runtime.Context;

public interface Expression extends Visitable, LocatedInText {
	public Literal compute(final Context closure) throws Throwable;
}
