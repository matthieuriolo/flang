package ch.ffhs.fac.flang.runtime;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import ch.ffhs.fac.flang.runtime.literals.Decimal;

public class Document extends Closure {
	public Document(final List<Instruction> instructions) {
		super(instructions);
	}
	
	@Override
	public Literal execute() throws Throwable {
		return Objects.requireNonNullElse(super.execute(), new Decimal(BigDecimal.ZERO));
	}
}
