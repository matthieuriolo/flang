package ch.ffhs.fac.flang.runtime.exceptions;

import ch.ffhs.fac.flang.parser.Location;

public abstract class LocatedInTextException extends RuntimeException {
	private static final long serialVersionUID = 1280326089463011818L;
	private final Location location;
	
	private static String locationFormat(final Location location) {
		return location != null ? "line: " + location.getLine() + " column: " + location.getColumn() + " - " : "";
	}
	
	public LocatedInTextException(final String message, final Location location) {
		super(locationFormat(location) + message);
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
}
