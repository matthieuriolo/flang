package ch.ffhs.fac.flang.runtime.exceptions;

import ch.ffhs.fac.flang.parser.Location;

/**
 * Exception which represents that not reachable code has been found
 * @author matthieuriolo
 *
 */
public class DeadCodeException extends LocatedInTextException {
	private static final long serialVersionUID = -1512018645052939113L;

	public DeadCodeException(final Location location) {
		super("Unreachable code", location);
	}
}
