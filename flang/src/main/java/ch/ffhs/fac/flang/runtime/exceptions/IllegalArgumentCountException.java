package ch.ffhs.fac.flang.runtime.exceptions;

import ch.ffhs.fac.flang.parser.Location;

public class IllegalArgumentCountException extends LocatedInTextException {
	private static final long serialVersionUID = 6549098924983298647L;

	public IllegalArgumentCountException(final int expected, final int actual, final Location location) {
		super("Expected " + expected + " arguments but found " + actual, location);
	}
}
