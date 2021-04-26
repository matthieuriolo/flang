package ch.ffhs.fac.flang.runtime.instructions;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.expressions.LiteralWrapper;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class Return implements Instruction {
	private final Expression expression;
	
	public Return() {
		this.expression = new LiteralWrapper(Undefined.UNDEFINED);
	}
	
	public Return(final Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public Literal execute(Closure closure) throws Throwable {
		return expression.compute(closure);
	}
}
