package ch.ffhs.fac.flang.runtime.visitors.validators;

import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.runtime.expressions.FunctionCall;
import ch.ffhs.fac.flang.runtime.instructions.Assignment;
import ch.ffhs.fac.flang.runtime.instructions.For;
import ch.ffhs.fac.flang.runtime.instructions.If;
import ch.ffhs.fac.flang.runtime.instructions.ProcedureCall;
import ch.ffhs.fac.flang.runtime.instructions.Return;
import ch.ffhs.fac.flang.runtime.instructions.While;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.visitors.Traverser;

public class DeadCode extends Traverser {
	private boolean foundReturn = false;
	
	private void validateDeadCode() {
		if(foundReturn) {
			// TODO
			throw new RuntimeException("Dead code found");
		}
	}
	
	@Override
	public void visitInstructionReturn(Return instr) {
		validateDeadCode();
		super.visitInstructionReturn(instr);
		foundReturn = true;
	}
	
	@Override
	public void visitInstructionFor(For instr) {
		validateDeadCode();
		super.visitInstructionFor(instr);
		foundReturn = false;
	}
	
	@Override
	public void visitInstructionWhile(While instr) {
		validateDeadCode();
		super.visitInstructionWhile(instr);
		foundReturn = false;
	}
	
	@Override
	public void visitInstructionFunctionProcedure(ProcedureCall instr) {
		validateDeadCode();
		super.visitInstructionFunctionProcedure(instr);
		foundReturn = false;
	}
	
	@Override
	public void visitInstructionAssignment(Assignment instr) {
		validateDeadCode();
		super.visitInstructionAssignment(instr);
	}
	
	@Override
	public void visitInstructionIf(If instr) {
		validateDeadCode();
		super.visitInstructionIf(instr);
		foundReturn = false;
	}
}
