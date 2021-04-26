package ch.ffhs.fac.flang.runtime.std;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Closure.FunctionInterface;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class PrintFunction implements FunctionInterface {
	public static final String NAME = "print";
	
	@Override
	public Literal execute(Closure closure, List<Literal> parameters) {
		System.out.println("jsdfklj laksjflkjsafd");
		
		return Undefined.UNDEFINED;
	}

}
