package ch.ffhs.fac.flang.runtime.bases;

import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;

@FunctionalInterface
public interface FunctionBridgeBase {
	Literal execute(final Context closure, final List<Literal> parameters, final Location location) throws Throwable;
}