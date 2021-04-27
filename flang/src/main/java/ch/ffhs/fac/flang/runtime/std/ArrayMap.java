package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Closure.FunctionInterface;
import ch.ffhs.fac.flang.runtime.literals.Array;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.Literal;

public class ArrayMap implements FunctionInterface {
	public static final String NAME = "array_map";

	@Override
	public Literal execute(Closure closure, List<Literal> parameters) throws Throwable {
		if (parameters.size() != 2) {
			throw new Exception("Two arguments required");
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