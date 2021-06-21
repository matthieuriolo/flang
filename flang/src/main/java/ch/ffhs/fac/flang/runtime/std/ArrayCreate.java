package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.Context.FunctionInterface;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.literals.Array;

public class ArrayCreate implements FunctionInterface {
	public static final String NAME = "array_create";

	@Override
	public Literal execute(final Context closure, final List<Literal> parameters) throws Throwable {
		return new Array(parameters);
	}
}
