package ch.ffhs.fac.flang.parser.exceptions;

import java.util.List;

import ch.ffhs.fac.flang.parser.Location;

/**
 * Exception for grammar error inside the parser
 * @author matthieuriolo
 *
 */
public class ParserException extends RuntimeException {
	private static final long serialVersionUID = -405000998832329228L;
	final private Location location;
	final private List<String> values;
	
	public ParserException(final List<String> values, final Location location) {
		super("Illegal symbol at " + location.getLine() + ":" + location.getColumn() + " expected " + String.join(", ", values));
		this.location = location;
		this.values = values;
	}
	
	/**
	 * Getter for the location
	 * @return {@link Location}
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Getter for the expected tokens
	 * @return {@link List<String>}
	 */
	public List<String> getValues() {
		return values;
	}
}
