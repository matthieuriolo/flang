package ch.ffhs.fac.flang.runtime.exceptions;

import ch.ffhs.fac.flang.runtime.expressions.Identifier;

public class DuplicatedArgumentException extends LocatedInTextException {
	private static final long serialVersionUID = -8012165347473796849L;

	public DuplicatedArgumentException(final Identifier argument) {
		super("The argument '" + argument.getName() + "' has been declared multiple times", argument.getLocation());
	}
}
