package ch.ffhs.fac.flang.runtime;

import java.util.List;

import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public interface Literal extends Expression {
	public default Literal operationOr(final Literal right) {
		return ch.ffhs.fac.flang.runtime.literals.Boolean.of(toBoolean() || right.toBoolean());
	}
	
	public default Literal operationAnd(final Literal right) {
		return ch.ffhs.fac.flang.runtime.literals.Boolean.of(toBoolean() && right.toBoolean());
	}
	
	public default Literal operationPlus(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationMinus(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationAsterisk(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationSlash(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationNotEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationLess(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationLessEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationGreater(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationGreaterEqual(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationUnaryMinus() {
		return Undefined.UNDEFINED;
	}
	
	public default Literal functionalCall(final Context closure, final List<Literal> arguments) throws Throwable {
		return Undefined.UNDEFINED;
	}
	
	public boolean toBoolean();
	public String toString();
	public default String toHumanReadableString() {
		return toString();
	}
	
	public default Literal toDecimal(final Context closure) {
		return new Decimal(toBoolean());
	}
	
	@Override
	public default Literal compute(Context closure) throws Throwable {
		return this;
	}
}
