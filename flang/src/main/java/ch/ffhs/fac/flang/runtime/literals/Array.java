package ch.ffhs.fac.flang.runtime.literals;

import java.util.List;
import java.util.stream.Collectors;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Literal;

public class Array implements Literal {
	private final List<Literal> values;

	public Array(final List<Literal> values) {
		this.values = values;
	}

	@Override
	public java.lang.String toString() {
		return values.stream()
				.map(literal -> literal.toString())
				.collect(Collectors.joining(", ", "[", "]"));
	}

	@Override
	public boolean toBoolean(Closure closure) {
		return !values.isEmpty();
	}
}
