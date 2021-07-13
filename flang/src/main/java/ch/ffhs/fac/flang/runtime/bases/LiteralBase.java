package ch.ffhs.fac.flang.runtime.bases;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Boolean;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public abstract class LiteralBase extends LocatedInTextBase implements Literal {
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj)) {
			return true;
		}else if(obj instanceof Literal) {
			return this.computeEqual((Literal)obj) == Boolean.TRUE;
		}
	
		return false;
	}
	
	@Override
	public Literal computeOr(final Literal right) {
		return ch.ffhs.fac.flang.runtime.literals.Boolean.of(toBoolean() || right.toBoolean());
	}
	
	@Override
	public Literal computeAnd(final Literal right) {
		return ch.ffhs.fac.flang.runtime.literals.Boolean.of(toBoolean() && right.toBoolean());
	}
	
	@Override
	public Literal computePlus(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeMinus(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeAsterisk(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeSlash(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeNotEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeLess(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeLessEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeGreater(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeGreaterEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal computeUnaryMinus() {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal functionalCall(final Context closure, final List<Literal> arguments) throws Throwable {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public java.lang.String toHumanReadableString() {
		return toString();
	}
	
	@Override
	public Literal toDecimal(final Context closure) {
		return new Decimal(toBoolean());
	}
	
	@Override
	public Literal compute(Context closure) throws Throwable {
		return this;
	}
}
