package ch.ffhs.fac.flang.runtime;


import ch.ffhs.fac.flang.runtime.expressions.FunctionCall;
import ch.ffhs.fac.flang.runtime.expressions.operations.BiOperand;
import ch.ffhs.fac.flang.runtime.expressions.operations.UnaryOperand;
import ch.ffhs.fac.flang.runtime.instructions.Assignment;
import ch.ffhs.fac.flang.runtime.instructions.For;
import ch.ffhs.fac.flang.runtime.instructions.FunctionProcedure;
import ch.ffhs.fac.flang.runtime.instructions.If;
import ch.ffhs.fac.flang.runtime.instructions.Return;
import ch.ffhs.fac.flang.runtime.instructions.While;
import ch.ffhs.fac.flang.runtime.literals.Boolean;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.Identifier;
import ch.ffhs.fac.flang.runtime.literals.String;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public interface Visitor {
	public void visitClosure(final Closure obj);
	public void visitExpression(final Expression expr);
	public void visitInstruction(final Instruction instr);
	
	
	
	public void visitLiteralBoolean(final Boolean obj);
	public void visitLiteralDecimal(final Decimal obj);
	public void visitLiteralFunction(final Function obj);
	public void visitLiteralIdentifier(final Identifier obj);
	public void visitLiteralString(final String obj);
	public void visitLiteralUndefined(final Undefined obj);
	
	public void visitInstructionAssignment(final Assignment instr);
	public void visitInstructionFor(final For instr);
	public void visitInstructionFunctionProcedure(final FunctionProcedure instr);
	public void visitInstructionIf(final If instr);
	public void visitInstructionReturn(final Return instr);
	public void visitInstructionWhile(final While instr);
	
	public void visitExpressionFunctionCall(final FunctionCall expr);
	public void visitExpressionBiOperand(final BiOperand expr);
	public void visitExpressionUnaryOperand(final UnaryOperand expr);
}
