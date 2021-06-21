package ch.ffhs.fac.flang.runtime.std;

import java.io.BufferedReader;
import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.exceptions.IllegalArgumentCountException;

public class Read implements FunctionBridgeBase {
	public static final String NAME = "read";
	private static final int ARGUMENT_COUNT = 0;
	private final BufferedReader reader;

	public Read(final BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public Literal execute(final Context closure, final List<Literal> parameters, final Location location) throws Throwable {
		if (parameters.size() != ARGUMENT_COUNT) {
			throw new IllegalArgumentCountException(ARGUMENT_COUNT, parameters.size(), location);
		}
		
		return new ch.ffhs.fac.flang.runtime.literals.String(reader.readLine());
	}
}
