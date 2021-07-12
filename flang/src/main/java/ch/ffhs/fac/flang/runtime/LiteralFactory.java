package ch.ffhs.fac.flang.runtime;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.expressions.Identifier;
import ch.ffhs.fac.flang.runtime.literals.Boolean;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class LiteralFactory implements ch.ffhs.fac.flang.parser.interfaces.LiteralFactory {

	@Override
	public Literal createBooleanTrue() {
		return Boolean.TRUE;
	}

	@Override
	public Literal createBooleanFalse() {
		return Boolean.FALSE;
	}

	@Override
	public Literal createUndefined() {
		return Undefined.UNDEFINED;
	}

	@Override
	public Literal createString(final String str) {
		return new ch.ffhs.fac.flang.runtime.literals.String(str);
	}

	@Override
	public Literal createDecimal(String str) {
		return new Decimal(str);
	}

	@Override
	public Literal createDecimalZero() {
		return Decimal.ZERO;
	}

	@Override
	public Literal createDecimalOne() {
		return Decimal.ONE;
	}

	@Override
	public Literal createFunction(List<Instruction> instructions) {
		return new Function(instructions);
	}

	@Override
	public Literal createFunction(List<Identifier> parameters, List<Instruction> instructions) {
		return new Function(parameters, instructions);
	}

}
