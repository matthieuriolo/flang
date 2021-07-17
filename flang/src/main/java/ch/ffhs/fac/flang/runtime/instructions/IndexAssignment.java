package ch.ffhs.fac.flang.runtime.instructions;

import java.util.LinkedList;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;
import ch.ffhs.fac.flang.runtime.expressions.Identifier;

public class IndexAssignment extends LocatedInTextBase implements Instruction {
	private final Identifier identifier;
	private final LinkedList<Expression> indexes;
	private final Expression expression;
	
	public IndexAssignment(final Identifier identifier, final LinkedList<Expression> indexes, final Expression expression) {
		this.identifier = identifier;
		this.indexes = indexes;
		this.expression = expression;
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}
	
	public LinkedList<Expression> getIndexes() {
		return indexes;
	}
	
	public Expression getExpression() {
		return expression;
	}

	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visitInstructionIndexAssignment(this);
	}

	@Override
	public Literal execute(Context closure) throws Throwable {
		var lit = closure.getValue(identifier);
		final var iter = indexes.iterator();
		while(iter.hasNext()) {
			final var expr = iter.next();
			//detect last element
			if(iter.hasNext()) {
				lit = lit.getAccess(expr.compute(closure));
			}
		}
		lit.setAccess(indexes.getLast().compute(closure), expression.compute(closure));
		return null;
	}
}
