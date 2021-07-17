package ch.ffhs.fac.flang.parser;

import java.util.List;

public class ParserException extends RuntimeException {
	private static final long serialVersionUID = -405000998832329228L;
	final private Location location;
	final private List<String> values;
	
	public ParserException(final List<String> values, final Location location) {
		super("Illegal symbol at " + location.getLine() + ":" + location.getColumn() + " expected " + String.join(", ", values));
		this.location = location;
		this.values = values;
	}
}
