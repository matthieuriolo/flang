package ch.ffhs.fac.flang.runtime.literals;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Literal;

public class Array implements Literal {
	private final List<Literal> values;
	public Array(final List<Literal> values) {
		this.values = values;
	}
}
