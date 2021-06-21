package ch.ffhs.fac.flang.parser.interfaces;

import ch.ffhs.fac.flang.runtime.Context;

public interface Instruction extends Visitable {
	public Literal execute(final Context closure) throws Throwable;
}
