package ch.ffhs.fac.flang.runtime.instructions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.expressions.LiteralWrapper;
import ch.ffhs.fac.flang.runtime.literals.Identifier;
import ch.ffhs.fac.flang.runtime.literals.Undefined;
import ch.ffhs.fac.flang.runtime.literals.Decimal;

public class For implements Instruction {
	private final Identifier identifier;
	private final Expression from;
	private final Expression to;
	private final Expression by;
	private final List<Instruction> instructions;

	public For(final Identifier identifier, final BigDecimal from, final Expression to, final Expression by,
			final List<Instruction> instructions) {
		this(identifier, new LiteralWrapper(new Decimal(from)), to, by, instructions);
	}

	public For(final Identifier identifier, final BigDecimal from, final BigDecimal to, final Expression by,
			final List<Instruction> instructions) {
		this(identifier, new LiteralWrapper(new Decimal(from)), new LiteralWrapper(new Decimal(to)), by, instructions);
	}

	public For(final Identifier identifier, final BigDecimal from, final BigDecimal to, final BigDecimal by,
			final List<Instruction> instructions) {
		this(identifier, new LiteralWrapper(new Decimal(from)), new LiteralWrapper(new Decimal(to)),
				new LiteralWrapper(new Decimal(by)), instructions);
	}

	public For(final Identifier identifier, final Expression from, final BigDecimal to, final BigDecimal by,
			final List<Instruction> instructions) {
		this(identifier, from, new LiteralWrapper(new Decimal(to)), new LiteralWrapper(new Decimal(by)), instructions);
	}

	public For(final Identifier identifier, final Expression from, final Expression to, final BigDecimal by,
			final List<Instruction> instructions) {
		this(identifier, from, to, new LiteralWrapper(new Decimal(by)), instructions);
	}

	public For(final Identifier identifier, final BigDecimal from, final Expression to, final BigDecimal by,
			final List<Instruction> instructions) {
		this(identifier, new LiteralWrapper(new Decimal(from)), to, new LiteralWrapper(new Decimal(by)), instructions);
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
		final var f = ((Decimal) from.compute(closure)).getValue();
		final var t = ((Decimal) to.compute(closure)).getValue();
		final var b = ((Decimal) by.compute(closure)).getValue();

		// some minimalistic guards
		if (b.compareTo(BigDecimal.ZERO) == 0 || f.compareTo(t) > 0 && b.compareTo(BigDecimal.ZERO) < 0
				|| f.compareTo(t) < 0 && b.compareTo(BigDecimal.ZERO) > 0) {
			throw new Exception("'For' cannot be executed");
		}

		for (var i = f; i.compareTo(t) != 0; i = i.add(b)) {
			final var block = new Closure(closure, instructions);
			block.setValue(identifier, new Decimal(i));
			final var returnLiteral = block.execute();
			if (returnLiteral != null) {
				return returnLiteral;
			}
		}

		return null;
	}
}
