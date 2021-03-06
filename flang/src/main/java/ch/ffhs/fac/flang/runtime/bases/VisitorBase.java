package ch.ffhs.fac.flang.runtime.bases;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitable;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
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
 * Base class for visitors. This implementation will travel through every structure element (Deep first search)
 * @author matthieuriolo
 *
 */
public abstract class VisitorBase implements Visitor {
	/**
	 * Convenience method for visiting a {@link Visitable}
	 * @param host is a {@link Visitable} which should be visited
	 */
	private void visit(final Visitable host) {
		host.acceptVisitor(this);
	}
	
	/**
	 * Convenience method for visiting a list of {@link Visitable}
	 * @param hosts is a list of {@link Visitable} which should be visited
	 */
	private void visit(final List<? extends Visitable> hosts) {
		hosts.stream().forEach(host -> host.acceptVisitor(this));
	}
	
	@Override
	public void visitDocument(Document doc) {
		visit(doc.getInstructions());
	}

	@Override
	public void visitLiteral(Literal obj) {
		visit(obj);
	}


	@Override
	public void visitLiteralBoolean(Boolean obj) {}

	@Override
	public void visitLiteralDecimal(Decimal obj) {}

	@Override
	public void visitLiteralFunction(Function obj) {
		visit(obj.getInstructions());
	}

	@Override
	public void visitExpressionIdentifier(Identifier obj) {}

	@Override
	public void visitLiteralString(String obj) {}

	@Override
	public void visitLiteralUndefined(Undefined obj) {}
	
	@Override
	public void visitExpression(Expression expr) {
		visit(expr);
	}

	@Override
	public void visitInstruction(Instruction instr) {
		visit(instr);
	}

	@Override
	public void visitInstructionAssignment(Assignment instr) {
		visit(instr.getIdentifier());
		visit(instr.getExpression());
	}
	
	@Override
	public void visitInstructionIndexAssignment(IndexAssignment instr) {
		visit(instr.getIdentifier());
		visit(instr.getIndexes());
		visit(instr.getExpression());
	}

	@Override
	public void visitInstructionFor(For instr) {
		visit(instr.getIdentifier());
		visit(instr.getFrom());
		visit(instr.getTo());
		visit(instr.getBy());
		visit(instr.getInstructions());
	}

	@Override
	public void visitInstructionProcedureCall(ProcedureCall instr) {
		visit(instr.getSubject());
		visit(instr.getArguments());
	}

	@Override
	public void visitInstructionIf(If instr) {
		visit(instr.getCondition());
		visit(instr.getInstructions());
		visit(instr.getElseInstructions());
	}

	@Override
	public void visitInstructionReturn(Return instr) {
		visit(instr.getExpression());
	}

	@Override
	public void visitInstructionWhile(While instr) {
		visit(instr.getCondition());
		visit(instr.getInstructions());
	}

	@Override
	public void visitExpressionFunctionCall(FunctionCall expr) {
		visit(expr.getSubject());
		visit(expr.getArguments());
	}

	@Override
	public void visitExpressionBiOperand(BinaryOperation expr) {
		visit(expr.getLeft());
		visit(expr.getRight());
	}

	@Override
	public void visitExpressionUnaryOperand(UnaryOperation expr) {
		visit(expr.getOperand());
	}
	
	@Override
	public void visitExpressionArrayBuilder(ArrayBuilder expr) {
		visit(expr.getExpressions());
	}
	
	@Override
	public void visitExpressionAccess(Access expr) {
		visit(expr.getIdentifier());
		visit(expr.getExpressions());
	}
}
