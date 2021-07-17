package ch.ffhs.fac.flang.runtime.expressions;

import java.util.LinkedList;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;
/**
 * Expression when an identifier gets accessed like an array
 * @author matthieuriolo
 *
 */
public class Access extends LocatedInTextBase implements Expression {
	private final Identifier identifier;
	private final LinkedList<Expression> expressions;
	
	public Access(final Identifier identifier, LinkedList<Expression> expressions) {
		this.identifier = identifier;
		this.expressions = expressions;
	}
	
	/**
	 * Getter for the identifier
	 * @return an {@link Identifier}
	 */
	public Identifier getIdentifier() {
		return identifier;
	}
	
	/**
	 * Getter for the expressions (indexes)
	 * @return a list of {@link Expression}
	 */
	public LinkedList<Expression> getExpressions() {
		return expressions;
	}
	
	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visitExpressionAccess(this);
	}

	@Override
	public Literal compute(Context closure) throws Throwable {
		var lit = closure.getValue(identifier);
		for(final var expr : expressions) {
			lit = lit.getAccess(expr.compute(closure));
		}
		return lit;
	}

}
