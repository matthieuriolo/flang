package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

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
		Literal last = Undefined.UNDEFINED;
		
		while(cond.toBoolean(closure)) {
			final var block = new Closure(closure, instructions);
			last = block.execute();
		}
		
		return last;
	}
}
