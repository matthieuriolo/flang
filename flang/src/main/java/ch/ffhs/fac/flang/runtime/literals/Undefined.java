package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitable;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;

public class Undefined extends LiteralBase {
	public static final Undefined UNDEFINED = new Undefined();
	
	@Override
	public java.lang.String toString() {
		return "UNDEFINED";
	}

	@Override
	public boolean toBoolean() {
		return false;
	}
	
	@Override
	public Literal toDecimal(final Context closure) {
		return this;
	}
	
	@Override
	public Literal operationEqual(final Literal right) {
		return Boolean.of(this == right);
	}
	
	@Override
	public Literal operationNotEqual(final Literal right) {
		return Boolean.of(this != right);
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteralUndefined(this);
	}
}
