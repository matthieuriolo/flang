package ch.ffhs.fac.flang.parser;

/**
 * Indicates the position an error, token or a structured element has been found in a text document by
 * wrapping the column and line position
 * @author matthieuriolo
 *
 */
public class Location {
	private final int column;
	private final int line;
	
	public Location(final int line, final int column) {
		this.line = line;
		this.column = column;
	}

	/**
	 * Returns the column position
	 * @return
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the line position
	 * @return
	 */
	public int getLine() {
		return line;
	}
}
