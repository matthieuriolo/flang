package ch.ffhs.fac.flang.parser.interfaces;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Context;

public interface Literal extends Expression {
	public Literal computeOr(final Literal right);
	public Literal computeAnd(final Literal right);
	public Literal computePlus(final Literal right);
	public Literal computeMinus(final Literal right);
	public Literal computeAsterisk(final Literal right);
	public Literal computeSlash(final Literal right);
	public Literal computeEqual(final Literal right);
	public Literal computeNotEqual(final Literal right);
	public Literal computeLess(final Literal right);
	public Literal computeLessEqual(final Literal right);
	public Literal computeGreater(final Literal right);
	public Literal computeGreaterEqual(final Literal right);
	public Literal computeUnaryMinus();
	
	public Literal functionalCall(final Context closure, final List<Literal> arguments) throws Throwable;
	
	public boolean toBoolean();
	public String toString();
	public String toHumanReadableString();
	public Literal toDecimal(final Context closure);
	
	@Override
	public Literal compute(Context closure) throws Throwable;
}
