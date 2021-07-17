package ch.ffhs.fac.flang.runtime.exceptions;

import ch.ffhs.fac.flang.parser.Location;

/**
 * Base Exception which is able to store the location of where the failure has been found
 * @author matthieuriolo
 *
 */
public abstract class LocatedInTextException extends RuntimeException {
	private static final long serialVersionUID = 1280326089463011818L;
	private final Location location;
	
	public LocatedInTextException(final String message, final Location location) {
		super(locationFormat(location) + message);
		this.location = location;
	}
	
	/**
	 * String formatting of the location
	 * @param location is a {@link Location}
	 * @return textual representation of the {@link Location}
	 */
	private static String locationFormat(final Location location) {
		return location != null ? "line: " + location.getLine() + " column: " + location.getColumn() + " - " : "";
	}
	
	/**
	 * Getter for the {@link Location}
	 * @return a {@link Location}
	 */
	public Location getLocation() {
		return location;
	}
}
