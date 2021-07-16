package ch.ffhs.fac.flang.parser.interfaces;


import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.expressions.Access;
import ch.ffhs.fac.flang.runtime.expressions.ArrayBuilder;
import ch.ffhs.fac.flang.runtime.expressions.BinaryOperation;
import ch.ffhs.fac.flang.runtime.expressions.FunctionCall;
import ch.ffhs.fac.flang.runtime.expressions.Identifier;
import ch.ffhs.fac.flang.runtime.expressions.UnaryOperation;
import ch.ffhs.fac.flang.runtime.instructions.Assignment;
import ch.ffhs.fac.flang.runtime.instructions.For;
import ch.ffhs.fac.flang.runtime.instructions.If;
import ch.ffhs.fac.flang.runtime.instructions.IndexAssignment;
import ch.ffhs.fac.flang.runtime.instructions.ProcedureCall;
import ch.ffhs.fac.flang.runtime.instructions.Return;
import ch.ffhs.fac.flang.runtime.instructions.While;
import ch.ffhs.fac.flang.runtime.literals.Boolean;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.String;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

/**
 * Visitor interface for structure elements returned from the parser
 * @author matthieuriolo
 *
 */
public interface Visitor {
	/**
	 * Visits a {@link Document}
	 * @param doc {@link Document}
	 */
	public void visitDocument(final Document doc);
	/**
	 * Fall-through for visiting a {@link Literal}
	 * @param obj {@link Literal}
	 */
	public void visitLiteral(final Literal obj);
	/**
	 * Fall-through for visiting a {@link Expression}
	 * @param expr {@link Expression}
	 */
	public void visitExpression(final Expression expr);
	/**
	 * Fall-through for visiting a {@link Instruction}
	 * @param instr {@link Instruction}
	 */
	public void visitInstruction(final Instruction instr);
	
	
	/**
	 * Visits a literal from type {@link Boolean}
	 * @param obj {@link Boolean}
	 */
	public void visitLiteralBoolean(final Boolean obj);
	/**
	 * Visits a literal from type {@link Decimal}
	 * @param obj {@link Decimal}
	 */
	public void visitLiteralDecimal(final Decimal obj);
	/**
	 * Visits a literal from type {@link Function}
	 * @param obj {@link Function}
	 */
	public void visitLiteralFunction(final Function obj);
	/**
	 * Visits a literal from type {@link String}
	 * @param obj {@link String}
	 */
	public void visitLiteralString(final String obj);
	/**
	 * Visits a literal from type {@link Undefined}
	 * @param obj {@link Undefined}
	 */
	public void visitLiteralUndefined(final Undefined obj);
	
	/**
	 * Visits an instruction from type {@link Assignment}
	 * @param instr {@link Assignment}
	 */
	public void visitInstructionAssignment(final Assignment instr);
	/**
	 * Visits an instruction from type {@link IndexAssignment}
	 * @param instr {@link IndexAssignment}
	 */
	public void visitInstructionIndexAssignment(final IndexAssignment instr);
	/**
	 * Visits an instruction from type {@link For}
	 * @param instr {@link For}
	 */
	public void visitInstructionFor(final For instr);
	/**
	 * Visits an instruction from type {@link ProcedureCall}
	 * @param instr {@link ProcedureCall}
	 */
	public void visitInstructionProcedureCall(final ProcedureCall instr);
	/**
	 * Visits an instruction from type {@link If}
	 * @param instr {@link If}
	 */
	public void visitInstructionIf(final If instr);
	/**
	 * Visits an instruction from type {@link Return}
	 * @param instr {@link Return}
	 */
	public void visitInstructionReturn(final Return instr);
	/**
	 * Visits an instruction from type {@link While}
	 * @param instr {@link While}
	 */
	public void visitInstructionWhile(final While instr);
	
	/**
	 * Visits an expression from type {@link Identifier}
	 * @param expr {@link Identifier}
	 */
	public void visitExpressionIdentifier(final Identifier expr);
	/**
	 * Visits an expression from type {@link FunctionCall}
	 * @param expr {@link FunctionCall}
	 */
	public void visitExpressionFunctionCall(final FunctionCall expr);
	/**
	 * Visits an expression from type {@link BinaryOperation}
	 * @param expr {@link BinaryOperation}
	 */
	public void visitExpressionBiOperand(final BinaryOperation expr);
	/**
	 * Visits an expression from type {@link UnaryOperation}
	 * @param expr {@link UnaryOperation}
	 */
	public void visitExpressionUnaryOperand(final UnaryOperation expr);
	/**
	 * Visits an expression from type {@link ArrayBuilder}
	 * @param expr {@link ArrayBuilder}
	 */
	public void visitExpressionArrayBuilder(final ArrayBuilder expr);
	/**
	 * Visits an expression from type {@link Access}
	 * @param expr {@link Access}
	 */
	public void visitExpressionAccess(final Access expr);
}
