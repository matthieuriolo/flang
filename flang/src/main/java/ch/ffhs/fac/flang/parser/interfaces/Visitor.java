package ch.ffhs.fac.flang.parser.interfaces;


import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.expressions.BinaryOperation;
import ch.ffhs.fac.flang.runtime.expressions.FunctionCall;
import ch.ffhs.fac.flang.runtime.expressions.Identifier;
import ch.ffhs.fac.flang.runtime.expressions.UnaryOperation;
import ch.ffhs.fac.flang.runtime.instructions.Assignment;
import ch.ffhs.fac.flang.runtime.instructions.For;
import ch.ffhs.fac.flang.runtime.instructions.ProcedureCall;
import ch.ffhs.fac.flang.runtime.instructions.If;
import ch.ffhs.fac.flang.runtime.instructions.Return;
import ch.ffhs.fac.flang.runtime.instructions.While;
import ch.ffhs.fac.flang.runtime.literals.Boolean;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.String;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public interface Visitor {
	public void visitDocument(final Document doc);
	public void visitLiteral(final Literal obj);
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
	public void visitInstructionFunctionProcedure(final ProcedureCall instr);
	public void visitInstructionIf(final If instr);
	public void visitInstructionReturn(final Return instr);
	public void visitInstructionWhile(final While instr);
	
	public void visitExpressionFunctionCall(final FunctionCall expr);
	public void visitExpressionBiOperand(final BinaryOperation expr);
	public void visitExpressionUnaryOperand(final UnaryOperation expr);
}
