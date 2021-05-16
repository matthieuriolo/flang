package ch.ffhs.fac.flang.runtime.std;

import java.io.Writer;
import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Closure.FunctionInterface;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class Print implements FunctionInterface {
	public static final String NAME = "print";
	private final Writer writer;

	public Print(final Writer writer) {
		this.writer = writer;
	}

	@Override
	public Literal execute(final Closure closure, final List<Literal> parameters) throws Throwable {
		for (final var param : parameters) {
			writer.write(param.toString());
		}
		writer.flush();
		return Undefined.UNDEFINED;
	}

}
