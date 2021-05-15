package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Closure.FunctionInterface;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.literals.Array;
import ch.ffhs.fac.flang.runtime.literals.Decimal;

public class ArrayGet implements FunctionInterface {
	public static final String NAME = "array_get";

	@Override
	public Literal execute(final Closure closure, final List<Literal> parameters) throws Throwable {
		if (parameters.size() != 2) {
			throw new Exception("Two arguments required");
		}

		final var array = parameters.get(0);
		final var index = parameters.get(1);

		if (!(array instanceof Array)) {
			throw new Exception("First argument must be an array");
		}

		if (!(index instanceof Decimal)) {
			throw new Exception("Second argument must be an integer");
		}

		return ((Array) array).get(((Decimal) index).getValue().intValueExact());
	}
}
