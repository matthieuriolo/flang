package ch.ffhs.fac.flang.runtime.expressions;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;

public class Identifier extends LocatedInTextBase implements Expression {
	private final java.lang.String name;
	
	public Identifier(final java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return name;
	}
	
	@Override
	public java.lang.String toString() {
		return getName();
	}
	
	@Override
	public Literal compute(Context closure) throws Throwable {
		return closure.getValue(getName());
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitExpressionIdentifier(this);
	}
}
