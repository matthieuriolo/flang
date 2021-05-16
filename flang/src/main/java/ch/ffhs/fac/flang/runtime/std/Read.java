package ch.ffhs.fac.flang.runtime.std;

import java.io.BufferedReader;
import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Closure.FunctionInterface;
import ch.ffhs.fac.flang.runtime.Literal;

public class Read implements FunctionInterface {
	public static final String NAME = "read";
	private final BufferedReader reader;

	public Read(final BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public Literal execute(Closure closure, List<Literal> parameters) throws Throwable {
		return new ch.ffhs.fac.flang.runtime.literals.String(reader.readLine());
	}
}
