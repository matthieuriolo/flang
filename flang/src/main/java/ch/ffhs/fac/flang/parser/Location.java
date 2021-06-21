package ch.ffhs.fac.flang.parser;

public class Location {
	private final int column;
	private final int line;
	
	public Location(final int line, final int column) {
		this.line = line;
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public int getLine() {
		return line;
	}
}
