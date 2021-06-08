package ch.ffhs.fac.flang.runtime.literals;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Visitable;
import ch.ffhs.fac.flang.runtime.Visitor;

public class Identifier implements Expression, Visitable {
	private final java.lang.String name;
	
	public Identifier(final java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return name;
	}
	
	@Override
	public Literal compute(Closure closure) throws Throwable {
		return closure.getValue(getName());
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteralIdentifier(this);
	}
}
