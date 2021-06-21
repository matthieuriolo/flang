package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.exceptions.IllegalArgumentCountException;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class CastDecimal implements FunctionBridgeBase {
	public static final String NAME = "cast_decimal";
	private static final int ARGUMENT_COUNT = 1;
	
	@Override
	public Literal execute(final Context closure, final List<Literal> parameters, final Location location) throws Throwable {
		if (parameters.size() != ARGUMENT_COUNT) {
			throw new IllegalArgumentCountException(ARGUMENT_COUNT, parameters.size(), location);
		}
		
		return parameters.get(0).toDecimal(closure);
	}
}
