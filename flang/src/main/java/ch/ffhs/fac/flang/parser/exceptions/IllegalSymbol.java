package ch.ffhs.fac.flang.parser.exceptions;

public class IllegalSymbol extends RuntimeException {
	private static final long serialVersionUID = 3420949309389535585L;
	private int line;
	private int column;
	private String value;
	public IllegalSymbol(int line, int column, String value) {
		super("Illegal symbol '" + value + "' at " + line + ":" + column);
		this.line = line;
		this.column = column;
		this.value = value;
	}
	public int getLine() {
		return line;
	}
	public int getColumn() {
		return column;
	}
	public String getValue() {
		return value;
	}
}
