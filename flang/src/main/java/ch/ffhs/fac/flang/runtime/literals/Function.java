package ch.ffhs.fac.flang.runtime.literals;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;

public class Function implements Literal {
	public static final Identifier MAGIC_ARGUMENTS = new Identifier("__arguments__");
	
	private final List<Identifier> parameters;
	private final List<Instruction> instructions;
	
	public Function(final List<Instruction> instructions) {
		this(List.of(), instructions);
	}
	
	public Function(final List<Identifier> parameters, final List<Instruction> instructions) {
		this.parameters = parameters;
		this.instructions = instructions;
	}

	public Literal functionalCall(final Closure closure, final List<Literal> arguments) throws Throwable {
		final var body = new Closure(closure, instructions);
		final var values = arguments.iterator();
		for (final var param : parameters) {
			Literal value = Undefined.UNDEFINED;
			if (values.hasNext()) {
				value = values.next();
			}
			body.setValue(param, value);
		}
		
		// magic variable containing all the arguments
		body.setValue(MAGIC_ARGUMENTS, new Array(arguments));
		return body.execute();
	}
	
	@Override
	public java.lang.String toString(final Closure closure) {
		final var buf = new StringBuffer();
		buf.append("<user defined function:");
		for (final var param : parameters) {
			buf.append(param.getName());
			buf.append(" ");
		}

		buf.append(">");

		return buf.toString();
	}
	
	@Override
	public boolean toBoolean(final Closure closure) {
		return true;
	}
	
	@Override
	public Literal toDecimal(Closure closure) {
		return Undefined.UNDEFINED;
	}
}
