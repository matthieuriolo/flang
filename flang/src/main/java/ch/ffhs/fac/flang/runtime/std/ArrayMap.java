package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.exceptions.IllegalArgumentCountException;
import ch.ffhs.fac.flang.runtime.literals.Array;
import ch.ffhs.fac.flang.runtime.literals.Function;

public class ArrayMap implements FunctionBridgeBase {
	public static final String NAME = "array_map";
	private static final int ARGUMENT_COUNT = 2;
	
	@Override
	public Literal execute(final Context closure, final List<Literal> parameters, final Location location) throws Throwable {
		if (parameters.size() != ARGUMENT_COUNT) {
			throw new IllegalArgumentCountException(ARGUMENT_COUNT, parameters.size(), location);
		}

		final var array = parameters.get(0);
		final var func = parameters.get(1);

		if (!(array instanceof Array)) {
			throw new Exception("First argument must be an array");
		}

		if (!(func instanceof Function)) {
			throw new Exception("Second argument must be an function");
		}

		return ((Array) array).map(closure, ((Function) func));
	}

}
