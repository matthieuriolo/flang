package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Routine;
import ch.ffhs.fac.flang.runtime.literals.Identifier;

public class FunctionProcedure extends Routine implements Instruction {
	public FunctionProcedure(final Identifier identifier, final List<Expression> arguments) {
		super(identifier, arguments);
	}
	
	public FunctionProcedure(final Expression subject, final List<Expression> arguments) {
		super(subject, arguments);
	}
	
	@Override
	public Literal execute(Closure closure) throws Throwable {
		return perform(closure);
	}
}
