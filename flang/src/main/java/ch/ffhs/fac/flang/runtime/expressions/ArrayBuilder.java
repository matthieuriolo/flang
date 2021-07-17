package ch.ffhs.fac.flang.runtime.expressions;

import java.util.LinkedList;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;
import ch.ffhs.fac.flang.runtime.literals.Array;

/**
 * Expression for creating an array
 * @author matthieuriolo
 *
 */
public class ArrayBuilder extends LocatedInTextBase implements Expression {
	private final LinkedList<Expression> expressions;
	

	public ArrayBuilder(final LinkedList<Expression> expressions) {
		this.expressions = expressions;
	}
	/**
	 * Getter for the expressions (values)
	 * @return a list of {@link Expression}
	 */
	public LinkedList<Expression> getExpressions() {
		return expressions;
	}


	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visitExpressionArrayBuilder(this);
	}


	@Override
	public Literal compute(Context closure) throws Throwable {
		final var values = new LinkedList<Literal>();
		for(final var expr : expressions) {
			values.add(expr.compute(closure));
		}
		return new Array(values);
	}
}
