package ch.ffhs.fac.flang.runtime.instructions;

import java.math.BigDecimal;
import java.util.List;

import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Visitable;
import ch.ffhs.fac.flang.runtime.Visitor;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Identifier;

public class For implements Instruction {
	private final Identifier identifier;
	private final Expression from;
	private final Expression to;
	private final Expression by;
	private final List<Instruction> instructions;

	public For(final Identifier identifier, final BigDecimal from, final Expression to, final Expression by,
			final List<Instruction> instructions) {
		this(identifier, new Decimal(from), to, by, instructions);
	}

	public For(final Identifier identifier, final BigDecimal from, final BigDecimal to, final Expression by,
			final List<Instruction> instructions) {
		this(identifier, new Decimal(from), new Decimal(to), by, instructions);
	}

	public For(final Identifier identifier, final BigDecimal from, final BigDecimal to, final BigDecimal by,
			final List<Instruction> instructions) {
		this(identifier, new Decimal(from), new Decimal(to),
				new Decimal(by), instructions);
	}

	public For(final Identifier identifier, final Expression from, final BigDecimal to, final BigDecimal by,
			final List<Instruction> instructions) {
		this(identifier, from, new Decimal(to), new Decimal(by), instructions);
	}

	public For(final Identifier identifier, final Expression from, final Expression to, final BigDecimal by,
			final List<Instruction> instructions) {
		this(identifier, from, to, new Decimal(by), instructions);
	}

	public For(final Identifier identifier, final BigDecimal from, final Expression to, final BigDecimal by,
			final List<Instruction> instructions) {
		this(identifier, new Decimal(from), to, new Decimal(by), instructions);
	}

	public For(final Identifier identifier, final Expression from, final Expression to, final Expression by,
			final List<Instruction> instructions) {
		this.identifier = identifier;
		this.from = from;
		this.to = to;
		this.by = by;
		this.instructions = instructions;
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}

	public Expression getFrom() {
		return from;
	}

	public Expression getTo() {
		return to;
	}

	public Expression getBy() {
		return by;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	@Override
	public Literal execute(Context closure) throws Throwable {
		final var f = ((Decimal) from.compute(closure)).getValue();
		final var t = ((Decimal) to.compute(closure)).getValue();
		final var b = ((Decimal) by.compute(closure)).getValue();
		final var positiveDirection = b.compareTo(BigDecimal.ZERO) > 0;

		// TODO: some minimalistic guards
		if (b.compareTo(BigDecimal.ZERO) == 0 || f.compareTo(t) > 0 && positiveDirection
				|| f.compareTo(t) < 0 && !positiveDirection) {
			throw new Exception("'For' cannot be executed");
		}

		for (var i = f; positiveDirection && i.compareTo(t) < 0
				|| !positiveDirection && i.compareTo(t) > 0; i = i.add(b)) {
			final var block = new Context(closure, instructions);
			block.setOwnValue(identifier, new Decimal(i));
			final var returnLiteral = block.execute();
			if (returnLiteral != null) {
				return returnLiteral;
			}
		}

		return null;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitInstructionFor(this);
	}
}
