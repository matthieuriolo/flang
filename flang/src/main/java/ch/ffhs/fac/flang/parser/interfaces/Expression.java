package ch.ffhs.fac.flang.parser.interfaces;

import ch.ffhs.fac.flang.runtime.Context;
/**
 * Structural element which represents an expression (something which needs to be calculated)
 * @author matthieuriolo
 *
 */
public interface Expression extends Visitable, LocatedInText {
	/**
	 * Calculates/computes the literal
	 * @param context the scope in which the expression is located in
	 * @return a {@link Literal}
	 * @throws Throwable
	 */
	public Literal compute(final Context context) throws Throwable;
}
