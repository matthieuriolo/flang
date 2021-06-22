package ch.ffhs.fac.flang.runtime.exceptions;

import ch.ffhs.fac.flang.parser.interfaces.Literal;

public class WrongTypeException extends LocatedInTextException {
	private static final long serialVersionUID = 595072433618280135L;

	public WrongTypeException(final int argumentPosition, final String expected, final Literal actual) {
		super("The " + argumentPosition + ". argument must be of type " + expected + ", but got " + actual.getTypeName(), actual.getLocation());
	}

}
