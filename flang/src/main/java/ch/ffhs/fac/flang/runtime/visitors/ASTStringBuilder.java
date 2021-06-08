package ch.ffhs.fac.flang.runtime.visitors;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Expression;
import ch.ffhs.fac.flang.runtime.Instruction;
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
	
	private void append(java.lang.String... strs) {
		append(java.lang.String.join(" ", strs));
	}
	
	private void append(java.lang.String str) {
		bld.append("  ".repeat(indentationCount));
		bld.append(str);
		bld.append("\n");
	}
	
	private void increment() {
		indentationCount++;
	}
	
	private void decrement() {
		indentationCount--;
		assert indentationCount > 0;
	}
	
	@Override
	public void visitClosure(Closure obj) {
		append("Closure");
		increment();
		
		for(final var instr : obj.getInstructions()) {
			instr.acceptVisitor(this);
		}
		
		decrement();
	}

	@Override
	public void visitLiteralBoolean(Boolean obj) {
		append("Boolean:", obj.toString());
	}

	@Override
	public void visitLiteralDecimal(Decimal obj) {
		append("Decimal:", obj.toString());
	}

	@Override
	public void visitLiteralFunction(Function obj) {
		append("Function:", obj.toString());
	}

	@Override
	public void visitLiteralIdentifier(Identifier obj) {
		append("Identifier:", obj.getName());
	}

	@Override
	public void visitLiteralString(String obj) {
		append("String:", obj.toString());
	}

	@Override
	public void visitLiteralUndefined(Undefined obj) {
		append("Undefined");
	}

	@Override
	public void visitInstructionAssignment(Assignment instr) {
		append("Assignment");
		increment();
		append("Identifier:", instr.getIdentifier().getName());
		decrement();
	}

	@Override
	public void visitInstructionFor(For instr) {
		append("For");
		increment();
		append("Identifier:", instr.getIdentifier().getName());
		append("From:", instr.getFrom().toString());
		append("To:", instr.getTo().toString());
		append("By:", instr.getBy().toString());
		increment();
		
		for(final var i : instr.getInstructions()) {
			i.acceptVisitor(this);
		}
		
		decrement();
		decrement();
	}

	@Override
	public void visitInstructionFunctionProcedure(FunctionProcedure instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitInstructionIf(If instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitInstructionReturn(Return instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitInstructionWhile(While instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitExpressionFunctionCall(FunctionCall expr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitExpressionBiOperand(BiOperand expr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitExpressionUnaryOperand(UnaryOperand expr) {
		// TODO Auto-generated method stub
		
	}
	
}
