package ch.ffhs.fac.flang.parser;

public class Location {
	private final int column;
	private final int row;
	
	public Location(int column, int row) {
		this.column = column;
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
}
