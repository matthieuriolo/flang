package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;

public class While implements Instruction {
	private final Expression condition;
	private final List<Instruction> instructions;
	
	public While(final Expression condition, final List<Instruction> instructions) {
		this.condition = condition;
		this.instructions = instructions;
	}
	
	@Override
	public Literal execute(Closure closure) throws Throwable {
		final var cond = condition.compute(closure);
		
		while(cond.toBoolean(closure)) {
			final var returnLiteral = new Closure(closure, instructions).execute();
			if(returnLiteral != null) {
				return returnLiteral;
			}
		}
		
		return null;
	}
}
