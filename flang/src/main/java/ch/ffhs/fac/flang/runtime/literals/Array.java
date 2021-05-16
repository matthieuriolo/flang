package ch.ffhs.fac.flang.runtime.literals;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Literal;

public class Array implements Literal {
	private final List<Literal> values;

	public Array(final List<Literal> values) {
		this.values = values;
	}

	public Literal get(final int index) {
		return values.get(index);
	}

	public Literal set(final int index, final Literal value) {
		return Objects.requireNonNullElse(values.set(index, value), Undefined.UNDEFINED);
	}

	public Array map(final Closure closure, final Function func) throws Throwable {
		final var list = new LinkedList<Literal>();
		for (final var val : values) {
			list.add(func.functionalCall(closure, List.of(val)));
		}
		return new Array(list);
	}

	public Array filter(final Closure closure, final Function func) throws Throwable {
		final var list = new LinkedList<Literal>();
		for (final var val : values) {
			final var ret = func.functionalCall(closure, List.of(val));
			if (ret.toBoolean(closure)) {
				list.add(val);
			}
		}
		return new Array(list);
	}

	@Override
	public java.lang.String toString(final Closure closure) {
		return values.stream()
				.map(literal -> literal.toHumanReadableString(closure))
				.collect(Collectors.joining(", ", "[", "]"));
	}

	@Override
	public boolean toBoolean(Closure closure) {
		return !values.isEmpty();
	}
}
