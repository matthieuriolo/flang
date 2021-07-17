package ch.ffhs.fac.flang.runtime.literals;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LiteralBase;

/**
 * Literal for the type "Undefined"
 * @author matthieuriolo
 *
 */
public class Undefined extends LiteralBase {
	public final static java.lang.String NAME = "Undefined";
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
	public Literal computeEqual(final Literal right) {
		return Boolean.of(this == right);
	}
	
	@Override
	public Literal computeNotEqual(final Literal right) {
		return Boolean.of(this != right);
	}
	
	@Override
	public java.lang.String getTypeName() {
		return Undefined.NAME;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteralUndefined(this);
	}
}
