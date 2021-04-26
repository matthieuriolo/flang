package ch.ffhs.fac.flang.runtime.expressions;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Literal;
@Deprecated // probably no needed
public class LiteralWrapper implements Expression {
	private Literal literal;
	public LiteralWrapper(Literal literal) {
		this.literal = literal;
	}
	
	@Override
	public Literal compute(Closure closure) {
		return literal;
	}
}
