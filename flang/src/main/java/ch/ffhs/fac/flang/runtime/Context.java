package ch.ffhs.fac.flang.runtime;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.Identifier;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class Context {
	private final Map<String, Literal> variables = new HashMap<String, Literal>();
	private final Context parent;
	private final List<Instruction> instructions;//TODO
	//TODO
	@FunctionalInterface
	public interface FunctionInterface {
		Literal execute(final Context closure, final List<Literal> parameters) throws Throwable;
	}
	
	private class FunctionBody implements Literal {
		private final FunctionInterface body;
		
		FunctionBody(final FunctionInterface body) {
			this.body = body;
		}
		
		public Literal functionalCall(final Context closure, final List<Literal> arguments) throws Throwable {
			return body.execute(closure, arguments);
		}

		@Override
		public boolean toBoolean() {
			return true;
		}
		
		@Override
		public String toString() {
			return "<developer defined function:" + getClass().getCanonicalName() + ">";
		}
		
		@Override
		public Literal toDecimal(final Context closure) {
			return Undefined.UNDEFINED;
		}

		@Override
		public void acceptVisitor(final Visitor visitor) {
			visitor.visitLiteral(this);
		}
	}
	
	public Context() {
		this.parent = null;
		this.instructions = new LinkedList<Instruction>();
	}
	
	public Context(final Context parent) {
		this.parent = parent;
		this.instructions = new LinkedList<Instruction>();
	}
	
	public Context(final Context parent, final List<Instruction> instructions) {
		this.parent = parent;
		this.instructions = instructions;
	}
	
	public Context(final List<Instruction> instructions) {
		this.parent = null;
		this.instructions = instructions;
	}
	
	public Context getParentClosure() {
		return parent;
	}
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	public Literal execute() throws Throwable {
		for(final var instr : instructions) {
			final var returnValue = instr.execute(this);
			if(returnValue != null) {
				return returnValue;
			}
		}
		
		return null;
	}
	
	private Context findClosureWithVariable(final String name) {
		if(variables.containsKey(name)) {
			return this;
		}else if(parent != null) {
			return parent.findClosureWithVariable(name);
		}
		
		return null;
	}
	
	public Literal getValue(final String name) {
		final var closure = findClosureWithVariable(name);
		if(closure != null) {
			return closure.variables.get(name);
		}
		
		return Undefined.UNDEFINED;
	}
	
	public void setValue(final String name, final Literal value) {
		final var closure = findClosureWithVariable(name);
		if(closure != null) {
			closure.variables.put(name, value);
		}else {
			variables.put(name, value);
		}
	}
	
	public void setValue(final Identifier identifier, final Literal value) {
		setValue(identifier.getName(), value);
	}
	
	public void setOwnValue(final Identifier identifier, final Literal value) {
		setOwnValue(identifier.getName(), value);
	}
	
	public void setOwnValue(final String name, final Literal value) {
		variables.put(name, value);
	}
	
	@Deprecated
	public void declareFunction(final String name, final List<Instruction> instructions) {
		setValue(name, new Function(List.of(), instructions));
	}
	
	public void declareFunction(final String name, final FunctionInterface body) {
		setValue(name, new FunctionBody(body));
	}
}
