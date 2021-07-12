package ch.ffhs.fac.flang.parser.interfaces;

import java.util.List;

// TODO
import ch.ffhs.fac.flang.runtime.expressions.Identifier;

public interface LiteralFactory {
	public Literal createBooleanTrue();
	public Literal createBooleanFalse();
	public Literal createUndefined();
	
	public Literal createString(final String str);
	
	public Literal createDecimal(final String str);
	public Literal createDecimalZero();
	public Literal createDecimalOne();
	
	public Literal createFunction(final List<Instruction> instructions);
	public Literal createFunction(final List<Identifier> parameters, final List<Instruction> instructions);
}
