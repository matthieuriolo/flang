package ch.ffhs.fac.flang.runtime.visitors.validators;

import ch.ffhs.fac.flang.parser.interfaces.LocatedInText;
import ch.ffhs.fac.flang.runtime.bases.VisitorBase;
import ch.ffhs.fac.flang.runtime.exceptions.DeadCodeException;
import ch.ffhs.fac.flang.runtime.instructions.Assignment;
import ch.ffhs.fac.flang.runtime.instructions.For;
import ch.ffhs.fac.flang.runtime.instructions.If;
import ch.ffhs.fac.flang.runtime.instructions.ProcedureCall;
import ch.ffhs.fac.flang.runtime.instructions.Return;
import ch.ffhs.fac.flang.runtime.instructions.While;
import ch.ffhs.fac.flang.runtime.literals.Function;

public class DeadCode extends VisitorBase {
	private boolean foundReturn = false;
	
	private void validateDeadCode(final LocatedInText located) {
		if(foundReturn) {
			throw new DeadCodeException(located.getLocation());
		}
	}
	
	@Override
	public void visitInstructionReturn(Return instr) {
		validateDeadCode(instr);
		super.visitInstructionReturn(instr);
		foundReturn = true;
	}
	
	@Override
	public void visitInstructionFor(For instr) {
		validateDeadCode(instr);
		super.visitInstructionFor(instr);
		foundReturn = false;
	}
	
	@Override
	public void visitInstructionWhile(While instr) {
		validateDeadCode(instr);
		super.visitInstructionWhile(instr);
		foundReturn = false;
	}
	
	@Override
	public void visitInstructionProcedureCall(ProcedureCall instr) {
		validateDeadCode(instr);
		super.visitInstructionProcedureCall(instr);
		foundReturn = false;
	}
	
	@Override
	public void visitInstructionAssignment(Assignment instr) {
		validateDeadCode(instr);
		super.visitInstructionAssignment(instr);
	}
	
	@Override
	public void visitInstructionIf(If instr) {
		validateDeadCode(instr);
		super.visitInstructionIf(instr);
		foundReturn = false;
	}
	
	@Override
	public void visitLiteralFunction(final Function obj) {
		super.visitLiteralFunction(obj);
		foundReturn = false;
	}
}
