package ch.ffhs.fac.flang.runtime.instructions;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Context;
import ch.ffhs.fac.flang.runtime.bases.FunctionBase;

/**
 * Instruction for a procedure call (aka ignoring the return statement of the function)
 * @author matthieuriolo
 *
 */
public class ProcedureCall extends FunctionBase implements Instruction {
	public ProcedureCall(final Expression subject) {
		super(subject, List.of());
	}
	
	public ProcedureCall(final Expression subject, final List<Expression> arguments) {
		super(subject, arguments);
	}
	
	@Override
	public Literal execute(Context closure) throws Throwable {
		perform(closure);
		return null;
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitInstructionProcedureCall(this);
	}
}
