package ch.ffhs.fac.flang.runtime.visitors;

import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitable;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.expressions.BinaryOperation;
import ch.ffhs.fac.flang.runtime.expressions.FunctionCall;
import ch.ffhs.fac.flang.runtime.expressions.UnaryOperation;
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

public abstract class Transferer implements Visitor {
	private void visit(final Visitable host) {
		host.acceptVisitor(this);
	}
	
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
	public void visitLiteralFunction(Function obj) {}

	@Override
	public void visitLiteralIdentifier(Identifier obj) {}

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
	public void visitInstructionFor(For instr) {
		visit(instr.getIdentifier());
		visit(instr.getFrom());
		visit(instr.getTo());
		visit(instr.getBy());
		visit(instr.getInstructions());
	}

	@Override
	public void visitInstructionFunctionProcedure(FunctionProcedure instr) {
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
}
