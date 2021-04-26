package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.runtime.Literal;

public class Identifier implements Literal {
	private final java.lang.String name;
	
	public Identifier(final java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return name;
	}
	
	@Override
	public java.lang.String toString() {
		return "<identifier: " + getName() + "";
	}
}
