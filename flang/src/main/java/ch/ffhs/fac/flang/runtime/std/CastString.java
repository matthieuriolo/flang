package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.literals.Undefined;
/**
 * Function bridge for converting any literal type to a {@link ch.ffhs.fac.flang.runtime.literals.String}
 * @author matthieuriolo
 *
 */
public class CastString implements FunctionBridgeBase {
	public static final String NAME = "cast_string";
	private static final int ARGUMENT_COUNT = 1;
	
	@Override
	public Literal execute(final Context closure, final List<Literal> parameters) throws Throwable {
		if (parameters.size() != ARGUMENT_COUNT) {
			return Undefined.UNDEFINED;
		}
		
		return new ch.ffhs.fac.flang.runtime.literals.String(parameters.get(0).toString());
	}
}
