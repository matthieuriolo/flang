package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class CastString implements FunctionBridgeBase {
	public static final String NAME = "cast_string";

	@Override
	public Literal execute(final Context closure, final List<Literal> parameters, final Location location) throws Throwable {
		if(!parameters.isEmpty()) {
			return new ch.ffhs.fac.flang.runtime.literals.String(parameters.get(0).toString());
		}

		return Undefined.UNDEFINED;
	}
}
