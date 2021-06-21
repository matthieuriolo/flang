package ch.ffhs.fac.flang.parser.interfaces;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Context;

public interface Literal extends Expression {
	// TODO rename to computeXY ?
	public Literal operationOr(final Literal right);
	public Literal operationAnd(final Literal right);
	public Literal operationPlus(final Literal right);
	public Literal operationMinus(final Literal right);
	public Literal operationAsterisk(final Literal right);
	public Literal operationSlash(final Literal right);
	public Literal operationEqual(final Literal right);
	public Literal operationNotEqual(final Literal right);
	public Literal operationLess(final Literal right);
	public Literal operationLessEqual(final Literal right);
	public Literal operationGreater(final Literal right);
	public Literal operationGreaterEqual(final Literal right);
	public Literal operationUnaryMinus();
	
	public Literal functionalCall(final Context closure, final List<Literal> arguments) throws Throwable;
	
	public boolean toBoolean();
	public String toString();
	public String toHumanReadableString();
	public Literal toDecimal(final Context closure);
	
	@Override
	public Literal compute(Context closure) throws Throwable;
}
