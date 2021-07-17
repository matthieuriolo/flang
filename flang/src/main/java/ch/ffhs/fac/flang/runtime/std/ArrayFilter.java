package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.literals.Array;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

/**
 * Function bridge for filtering the values in an {@link Array} 
 * @author matthieuriolo
 *
 */
public class ArrayFilter implements FunctionBridgeBase {
	public static final String NAME = "array_filter";
	private static final int ARGUMENT_COUNT = 2;
	@Override
	public Literal execute(final Context closure, final List<Literal> parameters) throws Throwable {
		if (parameters.size() != ARGUMENT_COUNT) {
			return Undefined.UNDEFINED;
		}

		final var array = parameters.get(0);
		final var func = parameters.get(1);

		if (!(array instanceof Array)) {
			return Undefined.UNDEFINED;
		}

		if (!(func instanceof Function)) {
			return Undefined.UNDEFINED;
		}

		return ((Array) array).filter(closure, ((Function) func));
	}
}
