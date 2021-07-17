package ch.ffhs.fac.flang.runtime.bases;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;

/**
 * Functional interface for representing a lambda expression which can be called like a regular function in flang
 * @author matthieuriolo
 *
 */
@FunctionalInterface
public interface FunctionBridgeBase {
	/**
	 * Will called when functionCall() is invoked
	 * 
	 * @param context the scope in which the function call is located in
	 * @param parameters a list of {@link Literal} which was passed as arguments to the function call
	 * @return the resulting {@link Literal}
	 * @throws Throwable
	 */
	Literal execute(final Context context, final List<Literal> parameters) throws Throwable;
}