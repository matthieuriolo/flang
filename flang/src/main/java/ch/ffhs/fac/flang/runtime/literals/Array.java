package ch.ffhs.fac.flang.runtime.literals;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LiteralBase;

public class Array extends LiteralBase {
	private final List<Literal> values;

	public Array(final List<Literal> values) {
		this.values = values;
	}

	public Literal get(final int index) {
		if(index >= values.size()) {
			return Undefined.UNDEFINED;
		}
		return values.get(index);
	}

	public Literal set(final int index, final Literal value) {
		if(index >= values.size()) {
			return Undefined.UNDEFINED;
		}
		return Objects.requireNonNullElse(values.set(index, value), Undefined.UNDEFINED);
	}

	public Array map(final Context closure, final Function func) throws Throwable {
		final var list = new LinkedList<Literal>();
		for (final var val : values) {
			list.add(func.functionalCall(closure, List.of(val)));
		}
		return new Array(list);
	}

	public Array filter(final Context closure, final Function func) throws Throwable {
		final var list = new LinkedList<Literal>();
		for (final var val : values) {
			final var ret = func.functionalCall(closure, List.of(val));
			if (ret.toBoolean()) {
				list.add(val);
			}
		}
		return new Array(list);
	}

	@Override
	public java.lang.String toString() {
		return values.stream()
				.map(literal -> literal.toHumanReadableString())
				.collect(Collectors.joining(", ", "[", "]"));
	}

	@Override
	public boolean toBoolean() {
		return !values.isEmpty();
	}

	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteral(this);
	}
}
