package ch.ffhs.fac.flang.runtime.std;

import java.io.BufferedReader;
import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;

/**
 * Function bridge for reading literals from an user input
 * @author matthieuriolo
 *
 */
public class Read implements FunctionBridgeBase {
	public static final String NAME = "read";
	private final BufferedReader reader;

	public Read(final BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public Literal execute(final Context closure, final List<Literal> parameters) throws Throwable {
		return new ch.ffhs.fac.flang.runtime.literals.String(reader.readLine());
	}
}
