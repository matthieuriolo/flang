package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class CastDecimal implements FunctionBridgeBase {
	public static final String NAME = "cast_decimal";

	@Override
	public Literal execute(final Context closure, final List<Literal> parameters) throws Throwable {
		if(!parameters.isEmpty()) {
			return parameters.get(0).toDecimal(closure);
		}

		return Undefined.UNDEFINED;
	}
}
