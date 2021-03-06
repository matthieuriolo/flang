package ch.ffhs.fac.flang.runtime.visitors;

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
 * ASTStringBuilder is a visitor which builds a textual representation of the AST
 * @author matthieuriolo
 *
 */
public class ASTStringBuilder implements Visitor {
	final private StringBuilder bld = new StringBuilder();
	private int indentationCount = 0;
	
	/**
	 * Retrieves the textual representation of the AST
	 * @return the AST as String
	 */
	public java.lang.String getString() {
		return bld.toString();
	}
	
	/**
	 * Convenience method which joins an array of strings together and append it to the buffer
	 * @param strs array of strings
	 */
	private void append(final java.lang.String... strs) {
		append(java.lang.String.join(" ", strs));
	}
	
	/**
	 * Convenience method which appends a string to the buffer (inclusive indentation and new line)
	 * @param str the string to be append
	 */
	private void append(final java.lang.String str) {
		bld.append("  ".repeat(indentationCount));
		bld.append(str);
		bld.append("\n");
	}
	
	/**
	 * Appends the textual representation of a visitable object (inclusive indentation)
	 * @param host which should be append to the buffer
	 */
	private void append(final Visitable host) {
		increment();
		host.acceptVisitor(this);
		decrement();
	}
	
	/**
	 * Appends the textual representation of a list of visitable object (inclusive indentation)
	 * @param hosts which should be append to the buffer
	 */
	private void append(final List<? extends Visitable> hosts) {
		increment();
		hosts.stream().forEach(host -> host.acceptVisitor(this));
		decrement();
	}
	
	/**
	 * Appends a literal to the buffer
	 * @param type is the name/type of the literal
	 * @param value is the textual value of the literal
	 */
	private void appendLiteral(final java.lang.String type, final java.lang.String value) {
		append(type + "(" + value + ")");
	}
	
	/**
	 * Increases internal indentation counter
	 */
	private void increment() {
		indentationCount++;
	}
	
	/**
	 * Decreases internal indentation counter
	 */
	private void decrement() {
		indentationCount--;
		assert indentationCount >= 0;
	}
	
	@Override
	public void visitDocument(Document doc) {
		append("Document");
		increment();
		append("- instructions:");
		increment();
		append(doc.getInstructions());
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
	public void visitExpressionIdentifier(Identifier obj) {
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
		increment();
		append("- Identifier:");
		append(instr.getIdentifier());
		append("- Expression:");
		append(instr.getExpression());
		decrement();
	}
	
	@Override
	public void visitInstructionIndexAssignment(IndexAssignment instr) {
		append("Index assignment");
		increment();
		append("- Identifier:");
		append(instr.getIdentifier());
		append("- Indexes:");
		append(instr.getIndexes());
		append("- Expression:");
		append(instr.getExpression());
		decrement();
	}

	@Override
	public void visitInstructionFor(For instr) {
		append("For");
		increment();
		append("- Identifier:");
		append(instr.getIdentifier());
		append("- From:");
		append(instr.getFrom());
		append("- To:");
		append(instr.getTo());
		append("- By:");
		append(instr.getBy());
		append("- instructions:");
		append(instr.getInstructions());
		decrement();
	}

	@Override
	public void visitInstructionProcedureCall(final ProcedureCall instr) {
		append("FunctionProcedure");
		increment();
		append("- subject:");
		append(instr.getSubject());
		if(!instr.getArguments().isEmpty()) {
			append("- arguments:");
			append(instr.getArguments());
		}
		decrement();
	}

	@Override
	public void visitInstructionIf(final If instr) {
		append("If");
		increment();
		append("- condition:");
		append(instr.getCondition());
		append("- instructions:");
		append(instr.getInstructions());
		if(!instr.getElseInstructions().isEmpty()) {
			append("- else instructions:");
			append(instr.getElseInstructions());
		}
		decrement();
	}

	@Override
	public void visitInstructionReturn(final Return instr) {
		append("Return");
		increment();
		append("- expression:");
		append(instr.getExpression());
		decrement();
	}

	@Override
	public void visitInstructionWhile(While instr) {
		append("While");
		increment();
		append("- condition:");
		append(instr.getCondition());
		append("- instructions:");
		append(instr.getInstructions());
		decrement();
	}

	@Override
	public void visitExpressionFunctionCall(FunctionCall expr) {
		append("FunctionCall");
		increment();
		append("- subject:");
		append(expr.getSubject());
		append("- arguments:");
		append(expr.getArguments());
		decrement();
	}

	@Override
	public void visitExpressionBiOperand(BinaryOperation expr) {
		append("BiOperand");
		increment();
		append("- operator:", expr.getType().getName());
		append("- left:");
		append(expr.getLeft());
		append("- right:");
		append(expr.getRight());
		decrement();
	}

	@Override
	public void visitExpressionUnaryOperand(UnaryOperation expr) {
		append("UnaryOperand");
		increment();
		append("- operator:", expr.getType().getName());
		append("- operand:");
		append(expr.getOperand());
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

	@Override
	public void visitExpressionArrayBuilder(final ArrayBuilder expr) {
		append("Array Builder:");
		increment();
		append(expr.getExpressions());
		decrement();
	}
	
	@Override
	public void visitExpressionAccess(final Access expr) {
		append("Access:");
		increment();
		append("- Identifier:");
		append(expr.getIdentifier());
		append("- Expressions:");
		append(expr.getExpressions());
		decrement();
	}
}
