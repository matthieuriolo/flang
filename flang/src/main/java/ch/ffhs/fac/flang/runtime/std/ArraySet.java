package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.exceptions.IllegalArgumentCountException;
import ch.ffhs.fac.flang.runtime.exceptions.WrongTypeException;
import ch.ffhs.fac.flang.runtime.literals.Array;
import ch.ffhs.fac.flang.runtime.literals.Decimal;

public class ArraySet implements FunctionBridgeBase {
	public static final String NAME = "array_set";
	private static final int ARGUMENT_COUNT = 3;
	
	@Override
	public Literal execute(final Context closure, final List<Literal> parameters, final Location location) throws Throwable {
		if(parameters.size() != ARGUMENT_COUNT) {
			throw new IllegalArgumentCountException(ARGUMENT_COUNT, parameters.size(), location);
		}
		
		final var array = parameters.get(0);
		final var index = parameters.get(1);
		final var value = parameters.get(2);
		
		if(!(array instanceof Array)) {
			throw new WrongTypeException(1, Array.NAME, array);
		}
		
		if(!(index instanceof Decimal)) {
			throw new WrongTypeException(2, Decimal.NAME, index);
		}
		
		return ((Array)array).set(((Decimal)index).getValue().intValueExact(), value);
	}
	
}
