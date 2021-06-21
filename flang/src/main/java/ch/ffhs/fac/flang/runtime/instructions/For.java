package ch.ffhs.fac.flang.runtime.instructions;

import java.math.BigDecimal;
import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.LocatedInTextBase;
import ch.ffhs.fac.flang.runtime.expressions.Identifier;
import ch.ffhs.fac.flang.runtime.literals.Decimal;

public class For extends LocatedInTextBase implements Instruction {
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

		for (var i = f; positiveDirection && i.compareTo(t) < 0
				|| !positiveDirection && i.compareTo(t) > 0; i = i.add(b)) {
			final var ctx = new Context(closure);
			ctx.setOwnValue(identifier, new Decimal(i));
			final var returnLiteral = ctx.execute(instructions);
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
