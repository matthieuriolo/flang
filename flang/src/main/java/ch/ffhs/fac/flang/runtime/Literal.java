package ch.ffhs.fac.flang.runtime;

import java.util.List;

import ch.ffhs.fac.flang.runtime.literals.Undefined;

public interface Literal {
	public default Literal operationOr(final Literal right) {
		return Undefined.UNDEFINED;
	}
	
	public default Literal operationAnd(final Literal right) {
		return Undefined.UNDEFINED;
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
	
	public default Literal functionalCall(final Closure closure, final List<Literal> arguments) {
		return Undefined.UNDEFINED;
	}
}
