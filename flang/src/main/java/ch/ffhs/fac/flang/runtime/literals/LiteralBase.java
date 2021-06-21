package ch.ffhs.fac.flang.runtime.literals;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;

public abstract class LiteralBase implements Literal {
	@Override
	public Literal operationOr(final Literal right) {
		return ch.ffhs.fac.flang.runtime.literals.Boolean.of(toBoolean() || right.toBoolean());
	}
	
	@Override
	public Literal operationAnd(final Literal right) {
		return ch.ffhs.fac.flang.runtime.literals.Boolean.of(toBoolean() && right.toBoolean());
	}
	
	@Override
	public Literal operationPlus(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationMinus(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationAsterisk(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationSlash(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationNotEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationLess(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationLessEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationGreater(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationGreaterEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal operationUnaryMinus() {
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
