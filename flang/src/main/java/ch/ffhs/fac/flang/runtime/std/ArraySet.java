package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.literals.Array;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class ArraySet implements FunctionBridgeBase {
	public static final String NAME = "array_set";
	
	@Override
	public Literal execute(final Context closure, final List<Literal> parameters) throws Throwable {
		if(parameters.size() != 3) {
			// TODO
			throw new Exception("Three arguments required");
		}
		
		final var array = parameters.get(0);
		final var index = parameters.get(1);
		final var value = parameters.get(2);
		
		if(!(array instanceof Array)) {
			return Undefined.UNDEFINED;
		}
		
		if(!(index instanceof Decimal)) {
			return Undefined.UNDEFINED;
		}
		
		return ((Array)array).set(((Decimal)index).getValue().intValueExact(), value);
	}
	
}
