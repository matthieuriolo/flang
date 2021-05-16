package ch.ffhs.fac.flang.runtime;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.Identifier;
import ch.ffhs.fac.flang.runtime.literals.Undefined;

public class Closure {
	private final Map<String, Literal> variables = new HashMap<String, Literal>();
	private final Closure parent;
	private final List<Instruction> instructions;
	
	@FunctionalInterface
	public interface FunctionInterface {
		Literal execute(final Closure closure, final List<Literal> parameters) throws Throwable;
	}
	
	private class FunctionBody implements Literal {
		private final FunctionInterface body;
		
		FunctionBody(final FunctionInterface body) {
			this.body = body;
		}
		
		public Literal functionalCall(final Closure closure, final List<Literal> arguments) throws Throwable {
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
		public Literal toDecimal(Closure closure) {
			return Undefined.UNDEFINED;
		}
	}
	
	public Closure() {
		this.parent = null;
		this.instructions = new LinkedList<Instruction>();
	}
	
	public Closure(final Closure parent) {
		this.parent = parent;
		this.instructions = new LinkedList<Instruction>();
	}
	
	public Closure(final Closure parent, final List<Instruction> instructions) {
		this.parent = parent;
		this.instructions = instructions;
	}
	
	public Closure(final List<Instruction> instructions) {
		this.parent = null;
		this.instructions = instructions;
	}
	
	public Closure getParentClosure() {
		return parent;
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
	
	private Closure findClosureWithVariable(final String name) {
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
	
	@Deprecated
	public void declareFunction(final String name, final List<Instruction> instructions) {
		setValue(name, new Function(List.of(), instructions));
	}
	
	public void declareFunction(final String name, final FunctionInterface body) {
		setValue(name, new FunctionBody(body));
	}
}
