package ch.ffhs.fac.flang.runtime;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitable;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.literals.Decimal;

public class Document extends Context implements Visitable {
	private final List<Instruction> instructions;
	
	public Document(final List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	public Literal execute() throws Throwable {
		return Objects.requireNonNullElse(execute(instructions), Decimal.ZERO);
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitDocument(this);
	}
}
