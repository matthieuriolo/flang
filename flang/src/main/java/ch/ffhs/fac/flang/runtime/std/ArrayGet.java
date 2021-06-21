package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.literals.Array;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class ArrayGet implements FunctionBridgeBase {
	public static final String NAME = "array_get";

	@Override
	public Literal execute(final Context closure, final List<Literal> parameters, final Location location) throws Throwable {
		if (parameters.size() != 2) {
			// TODO
			throw new Exception("Two arguments required");
		}

		final var array = parameters.get(0);
		final var index = parameters.get(1);

		if (!(array instanceof Array)) {
			return Undefined.UNDEFINED;
		}

		if (!(index instanceof Decimal)) {
			return Undefined.UNDEFINED;
		}

		return ((Array) array).get(((Decimal) index).getValue().intValueExact());
	}
}
