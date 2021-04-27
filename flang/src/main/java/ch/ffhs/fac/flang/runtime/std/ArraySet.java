package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Closure.FunctionInterface;
import ch.ffhs.fac.flang.runtime.literals.Array;
import ch.ffhs.fac.flang.runtime.literals.Integer;
import ch.ffhs.fac.flang.runtime.Literal;

public class ArraySet implements FunctionInterface {
	public static final String NAME = "array_set";
	
	@Override
	public Literal execute(Closure closure, List<Literal> parameters) throws Throwable {
		if(parameters.size() != 3) {
			throw new Exception("Three arguments required");
		}
		
		final var array = parameters.get(0);
		final var index = parameters.get(1);
		final var value = parameters.get(2);
		
		if(!(array instanceof Array)) {
			throw new Exception("First argument must be an array");
		}
		
		if(!(index instanceof Integer)) {
			throw new Exception("Second argument must be an integer");
		}
		
		return ((Array)array).set(((Integer)index).getValue(), value);
	}
	
}
