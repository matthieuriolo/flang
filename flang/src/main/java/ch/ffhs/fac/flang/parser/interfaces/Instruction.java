package ch.ffhs.fac.flang.parser.interfaces;

import ch.ffhs.fac.flang.runtime.Context;
/**
 * A single executable command of the language
 * @author matthieuriolo
 *
 */
public interface Instruction extends Visitable, LocatedInText {
	/**
	 * Executes the instruction and stores the value inside the context
	 * @param context the scope in which the expression is located in
	 * @return a {@link Literal} when a return statement has been encountered or else null
	 * @throws Throwable
	 */
	public Literal execute(final Context context) throws Throwable;
}
