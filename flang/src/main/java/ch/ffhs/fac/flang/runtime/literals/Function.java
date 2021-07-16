package ch.ffhs.fac.flang.runtime.literals;

import java.util.List;
import java.util.Objects;

import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LiteralBase;
import ch.ffhs.fac.flang.runtime.expressions.Identifier;

public class Function extends LiteralBase {
	public final static java.lang.String NAME = "Function";
	public static final Identifier MAGIC_ARGUMENTS = new Identifier("__arguments__");
	private Context closureCreator;
	private final List<Identifier> parameters;
	private final List<Instruction> instructions;

	public Function(final List<Instruction> instructions) {
		this(List.of(), instructions);
	}
	
	public Function(final List<Identifier> parameters, final List<Instruction> instructions) {
		this.parameters = parameters;
		this.instructions = instructions;
	}
	
	public List<Identifier> getParameters() {
		return parameters;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	@Override
	public Literal computeEqual(Literal right) {
		if(right instanceof Function) {
			return Boolean.of(this == right);
		}
		
		return super.computeEqual(right);
	}
	
	@Override
	public Literal computeNotEqual(Literal right) {
		if(right instanceof Function) {
			return Boolean.of(this != right);
		}
		
		return super.computeEqual(right);
	}
	
	@Override
	public Literal functionalCall(final Context closure, final List<Literal> arguments) throws Throwable {
		Objects.requireNonNull(closureCreator);
		final var body = new Context(closureCreator);
		final var values = arguments.iterator();
		for (final var param : parameters) {
			Literal value = Undefined.UNDEFINED;
			if (values.hasNext()) {
				value = values.next();
			}
			body.setOwnValue(param, value);
		}
		
		// magic variable containing all the arguments
		body.setOwnValue(MAGIC_ARGUMENTS, new Array(arguments));
		return body.execute(instructions);
	}
	
	@Override
	public java.lang.String toString() {
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
	public boolean toBoolean() {
		return true;
	}
	
	@Override
	public Literal toDecimal(Context closure) {
		return Undefined.UNDEFINED;
	}
	
	@Override
	public Literal compute(Context closure) throws Throwable {
		closureCreator = closure;
		return this;
	}
	
	@Override
	public java.lang.String getTypeName() {
		return Function.NAME;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitLiteralFunction(this);
	}
}
