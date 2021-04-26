package ch.ffhs.fac.flang.runtime.literals;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;

public class Function implements Literal {
	public static final Identifier MAGIC_ARGUMENTS = new Identifier("__arguments__");
	
	private final List<Identifier> parameters;
	private final List<Instruction> instructions;

	public Function(final List<Identifier> parameters, final List<Instruction> instructions) {
		this.parameters = parameters;
		this.instructions = instructions;
	}

	public Literal functionalCall(final Closure closure, final List<Literal> arguments) {
		final var body = new Closure(closure, instructions);
		final var values = arguments.iterator();
		for (final var identifier : parameters) {
			Literal value = Undefined.UNDEFINED;
			if (values.hasNext()) {
				value = values.next();
			}
			body.setValue(identifier, value);
		}
		
		// magic variable containing all the arguments
		body.setValue(MAGIC_ARGUMENTS, new Array(arguments));
		return body.execute(closure);
	}
}
