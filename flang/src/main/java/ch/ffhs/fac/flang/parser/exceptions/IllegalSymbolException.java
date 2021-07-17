package ch.ffhs.fac.flang.parser.exceptions;

import ch.ffhs.fac.flang.parser.Location;

/**
 * Exception thrown by the lexer when an invalid token has been encountered
 * @author matthieuriolo
 *
 */
public class IllegalSymbolException extends RuntimeException {
	private static final long serialVersionUID = 3420949309389535585L;
	final private Location location;
	final private String value;
	
	public IllegalSymbolException(final String value, final Location location) {
		super("Illegal symbol '" + value + "' at " + location.getLine() + ":" + location.getColumn());
		this.location = location;
		this.value = value;
	}
	
	/**
	 * Returns the location where invalid token has been found
	 * @return {@link Location}
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Returns the string representation of the token which could been read
	 * @return {@link String}
	 */
	public String getValue() {
		return value;
	}
}
