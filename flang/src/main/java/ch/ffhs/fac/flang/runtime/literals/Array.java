package ch.ffhs.fac.flang.runtime.literals;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LiteralBase;

/**
 * Literal for the type "Array"
 * @author matthieuriolo
 *
 */
public class Array extends LiteralBase {
	public final static java.lang.String NAME = "Array";
	private final List<Literal> values;

	public Array(final List<Literal> values) {
		this.values = values;
	}

	/**
	 * Retrieves the value as position index
	 * @param index is the position inside the array
	 * @return undefined if index is greater than the array size of the value at position index
	 */
	public Literal get(final int index) {
		if(index >= values.size()) {
			return Undefined.UNDEFINED;
		}
		return values.get(index);
	}

	/**
	 * Sets the value as position index
	 * @param index is the position inside the array
	 * @param value is the new value which should be set
	 * @return undefined if index is greater than the array size of the value at position index
	 */
	public Literal set(final int index, final Literal value) {
		if(index >= values.size()) {
			return Undefined.UNDEFINED;
		}
		return Objects.requireNonNullElse(values.set(index, value), Undefined.UNDEFINED);
	}

	/**
	 * Applies a function to every element inside array and returns a new array
	 * @param context is the scope in which the literal gets called from
	 * @param func is a {@link Function} which should be applied to the elements
	 * @return a new array with the converted values
	 * @throws Throwable
	 */
	public Array map(final Context context, final Function func) throws Throwable {
		final var list = new LinkedList<Literal>();
		for (final var val : values) {
			list.add(func.functionalCall(context, List.of(val)));
		}
		return new Array(list);
	}

	/**
	 * Filters the elements based on a function which should return true (keep in array) or false
	 * @param context is the scope in which the literal gets called from
	 * @param func is a {@link Function} which should be applied to the elements
	 * @return a new array with the filtered values
	 * @throws Throwable
	 */
	public Array filter(final Context context, final Function func) throws Throwable {
		final var list = new LinkedList<Literal>();
		for (final var val : values) {
			final var ret = func.functionalCall(context, List.of(val));
			if (ret.toBoolean()) {
				list.add(val);
			}
		}
		return new Array(list);
	}
	
	@Override
	public Literal getAccess(Literal idx) {
		if (!(idx instanceof Decimal)) {
			return Undefined.UNDEFINED;
		}

		return get(((Decimal) idx).getValue().intValueExact());
	}
	
	@Override
	public void setAccess(Literal idx, Literal value) {
		if(!(idx instanceof Decimal)) {
			return;
		}
		
		set(((Decimal)idx).getValue().intValueExact(), value);
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
	public java.lang.String getTypeName() {
		return Array.NAME;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteral(this);
	}
}
