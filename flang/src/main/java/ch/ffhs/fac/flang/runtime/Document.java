package ch.ffhs.fac.flang.runtime;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitable;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.exceptions.LocatedInTextException;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.visitors.validators.DeadCode;
import ch.ffhs.fac.flang.runtime.visitors.validators.DuplicateArgument;

/**
 * The Document class contains the whole parsed tree and provides some methods for executing the content
 * @author matthieuriolo
 *
 */
public class Document extends Context implements Visitable {
	private final List<Instruction> instructions;
	@SuppressWarnings("serial")
	private final List<Visitor> validators = new LinkedList<Visitor>() {{
		add(new DeadCode());
		add(new DuplicateArgument());
	}};
	
	public Document(final List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
	/**
	 * Getter for instructions
	 * @return list of instructions
	 */
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	/**
	 * Executes the AST and returns the last returned literal
	 * @return literal from the last return statement
	 * @throws Throwable
	 */
	public Literal execute() throws Throwable {
		return Objects.requireNonNullElse(execute(instructions), Decimal.ZERO);
	}
	
	/**
	 * Validates the tree and throws an exception if there was illegal construct in the tree
	 * @throws LocatedInTextException
	 */
	public void validate() throws LocatedInTextException {
		validators.stream().forEach(validator -> validator.visitDocument(this));
	}
	
	@Override
	public void acceptVisitor(final Visitor visitor) {
		visitor.visitDocument(this);
	}
}
