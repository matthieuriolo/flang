package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Closure.FunctionInterface;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class CastString implements FunctionInterface {
	public static final String NAME = "cast_string";

	@Override
	public Literal execute(final Closure closure, final List<Literal> parameters) throws Throwable {
		if(!parameters.isEmpty()) {
			return new ch.ffhs.fac.flang.runtime.literals.String(parameters.get(0).toString());
		}

		return Undefined.UNDEFINED;
	}
}