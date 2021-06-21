package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.literals.Array;

public class ArrayCreate implements FunctionBridgeBase {
	public static final String NAME = "array_create";

	@Override
	public Literal execute(final Context closure, final List<Literal> parameters, final Location location) throws Throwable {
		return new Array(parameters);
	}
}
