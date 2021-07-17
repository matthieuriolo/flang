package ch.ffhs.fac.flang.runtime.expressions;

import java.util.List;
import java.util.Objects;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitable;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBase;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

/**
 * Expression when a literal gets called like a function
 * @author matthieuriolo
 *
 */
public class FunctionCall extends FunctionBase implements Expression, Visitable {
	public FunctionCall(final Expression subject) {
		super(subject);
	}
	
	public FunctionCall(final Expression subject, final List<Expression> arguments) {
		super(subject, arguments);
	}
	
	@Override
	public Literal compute(Context closure) throws Throwable {
		return Objects.requireNonNullElse(perform(closure), Undefined.UNDEFINED);
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitExpressionFunctionCall(this);
	}
}
