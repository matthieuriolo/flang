package ch.ffhs.fac.flang.runtime.literals;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Literal;

public class Identifier implements Literal {
	private final java.lang.String name;
	
	public Identifier(final java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return name;
	}
	
	public Literal functionalCall(final Closure closure, final List<Literal> arguments) throws Throwable {
		final var resolvedSubject = closure.getValue(getName());
		return resolvedSubject.functionalCall(closure, arguments);
	}
	
	@Override
	public java.lang.String toString() {
		return "<identifier: " + getName() + "";
	}
}
