package ch.ffhs.fac.flang.runtime.visitors;

import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
import ch.ffhs.fac.flang.runtime.Literal;
import ch.ffhs.fac.flang.runtime.Visitor;
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

public class ASTStringBuilder implements Visitor {
	final private StringBuilder bld = new StringBuilder();
	private int indentationCount = 0;
	
	public java.lang.String getString() {
		return bld.toString();
	}
	
	private void append(final java.lang.String... strs) {
		append(java.lang.String.join(" ", strs));
	}
	
	private void append(final java.lang.String str) {
		bld.append("  ".repeat(indentationCount));
		bld.append(str);
		bld.append("\n");
	}
	
	private void appendLiteral(final java.lang.String type, final java.lang.String value) {
		append(type + "(" + value + ")");
	}
	
	private void increment() {
		indentationCount++;
	}
	
	private void decrement() {
		indentationCount--;
		assert indentationCount > 0;
	}
	
	@Override
	public void visitDocument(Document doc) {
		append("Document");
		increment();
		append("- instructions:");
		increment();
		doc.getInstructions().stream().forEach(i -> i.acceptVisitor(this));
		decrement();
		decrement();
	}

	@Override
	public void visitLiteralBoolean(Boolean obj) {
		appendLiteral("Boolean", obj.toString());
	}

	@Override
	public void visitLiteralDecimal(Decimal obj) {
		appendLiteral("Decimal", obj.toString());
	}

	@Override
	public void visitLiteralFunction(Function obj) {
		appendLiteral("Function", obj.toString());
	}

	@Override
	public void visitLiteralIdentifier(Identifier obj) {
		appendLiteral("Identifier", obj.toString());
	}

	@Override
	public void visitLiteralString(String obj) {
		appendLiteral("String", obj.toString());
	}

	@Override
	public void visitLiteralUndefined(Undefined obj) {
		append("Undefined");
	}

	@Override
	public void visitInstructionAssignment(Assignment instr) {
		append("Assignment");
		append("- Identifier:", instr.getIdentifier().getName());
		append("- Expression:");
		increment();
		instr.getExpression().acceptVisitor(this);
		decrement();
	}

	@Override
	public void visitInstructionFor(For instr) {
		append("For");
		increment();
		append("- Identifier:", instr.getIdentifier().getName());
		append("- From:", instr.getFrom().toString());
		append("- To:", instr.getTo().toString());
		append("- By:", instr.getBy().toString());
		append("- instructions:", instr.getBy().toString());
		increment();
		instr.getInstructions().stream().forEach(i -> i.acceptVisitor(this));
		decrement();
		decrement();
	}

	@Override
	public void visitInstructionFunctionProcedure(FunctionProcedure instr) {
		append("FunctionProcedure");
		increment();
		append("- subject:");
		instr.getSubject().acceptVisitor(this);
		append("- arguments:");
		increment();
		instr.getArguments().stream().forEach(i -> i.acceptVisitor(this));
		decrement();
		decrement();
	}

	@Override
	public void visitInstructionIf(If instr) {
		append("If");
		increment();
		append("- condition:");
		instr.getCondition().acceptVisitor(this);
		append("- instructions:");
		increment();
		instr.getInstructions().stream().forEach(i -> i.acceptVisitor(this));
		decrement();
		
		if(!instr.getElseInstructions().isEmpty()) {
			append("- else instructions:");
			increment();
			instr.getElseInstructions().stream().forEach(i -> i.acceptVisitor(this));
			decrement();
		}
		
		decrement();
	}

	@Override
	public void visitInstructionReturn(Return instr) {
		append("Return");
		increment();
		append("- expression:");
		instr.getExpression().acceptVisitor(this);
		decrement();
	}

	@Override
	public void visitInstructionWhile(While instr) {
		append("While");
		increment();
		append("- condition:");
		instr.getCondition().acceptVisitor(this);
		append("- instructions:");
		increment();
		instr.getInstructions().stream().forEach(i -> i.acceptVisitor(this));
		decrement();
		decrement();
	}

	@Override
	public void visitExpressionFunctionCall(FunctionCall expr) {
		append("FunctionCall");
		increment();
		append("- subject:");
		expr.getSubject().acceptVisitor(this);
		append("- arguments:");
		increment();
		expr.getArguments().stream().forEach(i -> i.acceptVisitor(this));
		decrement();
		decrement();
	}

	@Override
	public void visitExpressionBiOperand(BiOperand expr) {
		append("BiOperand");
		increment();
		append("- operator:", expr.getType().getName());
		append("- left:");
		expr.getLeft().acceptVisitor(this);
		append("- right:");
		expr.getRight().acceptVisitor(this);
		decrement();
	}

	@Override
	public void visitExpressionUnaryOperand(UnaryOperand expr) {
		append("UnaryOperand");
		increment();
		append("- operator:", expr.getType().getName());
		append("- operand:");
		expr.getOperand().acceptVisitor(this);
		decrement();
	}

	@Override
	public void visitLiteral(final Literal obj) {
		append("Unknown literal:", obj.getClass().getName());
	}
	
	@Override
	public void visitExpression(final Expression expr) {
		append("Unknown expression:", expr.getClass().getName());
	}
	
	@Override
	public void visitInstruction(final Instruction instr) {
		append("Unknown instruction:", instr.getClass().getName());
	}
	
}
