package ch.ffhs.fac.flang.runtime.std;

import java.io.Writer;
import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class Print implements FunctionBridgeBase {
	public static final String NAME = "print";
	private final Writer writer;

	public Print(final Writer writer) {
		this.writer = writer;
	}

	@Override
	public Literal execute(final Context closure, final List<Literal> parameters) throws Throwable {
		for (final var param : parameters) {
			writer.write(param.toString());
		}
		writer.flush();
		return Undefined.UNDEFINED;
	}

}
