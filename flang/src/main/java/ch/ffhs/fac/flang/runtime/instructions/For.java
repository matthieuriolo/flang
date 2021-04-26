package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.expressions.LiteralWrapper;
import ch.ffhs.fac.flang.runtime.literals.Identifier;
import ch.ffhs.fac.flang.runtime.literals.Undefined;
import ch.ffhs.fac.flang.runtime.literals.Integer;

public class For implements Instruction {
	private final Identifier identifier;
	private final Expression from;
	private final Expression to;
	private final Expression by;
	private final List<Instruction> instructions;

	public For(final Identifier identifier, final int from, final Expression to, final Expression by,
			final List<Instruction> instructions) {
		this(identifier, new LiteralWrapper(new Integer(from)), to, by, instructions);
	}
	
	public For(final Identifier identifier, final int from, final int to, final Expression by,
			final List<Instruction> instructions) {
		this(identifier, new LiteralWrapper(new Integer(from)), new LiteralWrapper(new Integer(to)), by, instructions);
	}
	
	public For(final Identifier identifier, final int from, final int to, final int by,
			final List<Instruction> instructions) {
		this(identifier, new LiteralWrapper(new Integer(from)), new LiteralWrapper(new Integer(to)), new LiteralWrapper(new Integer(by)), instructions);
	}
	
	public For(final Identifier identifier, final Expression from, final int to, final int by,
			final List<Instruction> instructions) {
		this(identifier, from, new LiteralWrapper(new Integer(to)), new LiteralWrapper(new Integer(by)), instructions);
	}
	
	public For(final Identifier identifier, final Expression from, final Expression to, final int by,
			final List<Instruction> instructions) {
		this(identifier, from, to, new LiteralWrapper(new Integer(by)), instructions);
	}
	
	public For(final Identifier identifier, final int from, final Expression to, final int by,
			final List<Instruction> instructions) {
		this(identifier, new LiteralWrapper(new Integer(from)), to, new LiteralWrapper(new Integer(by)), instructions);
	}
	
	public For(final Identifier identifier, final Expression from, final Expression to, final Expression by,
			final List<Instruction> instructions) {
		this.identifier = identifier;
		this.from = from;
		this.to = to;
		this.by = by;
		this.instructions = instructions;
	}

	@Override
	public Literal execute(Closure closure) throws Throwable {
		final var f = ((Integer) from.compute(closure)).getValue();
		final var t = ((Integer) to.compute(closure)).getValue();
		final var b = ((Integer) by.compute(closure)).getValue();

		// some minimalistic guards
		if (b == 0 || f < t && b < 0 || f > t && b > 0) {
			throw new Exception("'For' cannot be executed");
		}

		Literal last = Undefined.UNDEFINED;
		for (int i = f; f < t && i < t || i > t; i += b) {
			final var block = new Closure(closure, instructions);
			block.setValue(identifier, new Integer(i));
			last = block.execute();
		}

		return last;
	}
}
