package ch.ffhs.fac.flang.parser;

public class IllegalSymbolException extends RuntimeException {
	private static final long serialVersionUID = 3420949309389535585L;
	final private Location location;
	final private String value;
	
	public IllegalSymbolException(final String value, final Location location) {
		super("Illegal symbol '" + value + "' at " + location.getLine() + ":" + location.getColumn());
		this.location = location;
		this.value = value;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getValue() {
		return value;
	}
}
