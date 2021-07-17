package ch.ffhs.fac.flang.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.ffhs.fac.flang.parser.interfaces.Instruction;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.bases.FunctionBridgeBase;
import ch.ffhs.fac.flang.runtime.bases.LiteralBase;
import ch.ffhs.fac.flang.runtime.expressions.Identifier;
import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.Undefined;
/**
 * Contains all the declared variables and provides a way of nesting scopes
 * @author matthieuriolo
 *
 */
public class Context {
	private final Map<String, Literal> variables = new HashMap<String, Literal>();
	private final Context parent;
	
	/**
	 * Internal class which creates a function from the functional interface {@link FunctionBridgeBase}
	 * @author matthieuriolo
	 *
	 */
	private class FunctionBridge extends LiteralBase {
		private final FunctionBridgeBase body;
		
		FunctionBridge(final FunctionBridgeBase body) {
			this.body = body;
		}
		
		@Override
		public Literal functionalCall(final Context context, final List<Literal> arguments) throws Throwable {
			return body.execute(context, arguments);
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
		public Literal toDecimal(final Context context) {
			return Undefined.UNDEFINED;
		}

		@Override
		public void acceptVisitor(final Visitor visitor) {
			visitor.visitLiteral(this);
		}

		@Override
		public String getTypeName() {
			return Function.NAME;
		}
	}
	
	public Context() {
		this.parent = null;
	}
	
	public Context(final Context parent) {
		this.parent = parent;
	}
	
	/**
	 * Getter for parent context
	 * @return
	 */
	public Context getParentContext() {
		return parent;
	}
	
	/**
	 * Executes a given list of instructions inside this scope.
	 * @param instructions
	 * @return null if there has been no return statement or the literal
	 * @throws Throwable
	 */
	public Literal execute(final List<Instruction> instructions) throws Throwable {
		for(final var instr : instructions) {
			final var returnValue = instr.execute(this);
			if(returnValue != null) {
				return returnValue;
			}
		}
		
		return null;
	}
	
	/**
	 * Searches for a parent context which defines a given variable
	 * @param name is the variable to search for
	 * @return null if no parent closes declares the variable or the context which declares the variable
	 */
	private Context findContextWithVariable(final String name) {
		if(variables.containsKey(name)) {
			return this;
		}else if(parent != null) {
			return parent.findContextWithVariable(name);
		}
		
		return null;
	}
	
	/**
	 * Returns the value of a variable defined in this or one of the parent contexts
	 * @param name is the name of the variable
	 * @return the value of the variable
	 */
	public Literal getValue(final String name) {
		final var context = findContextWithVariable(name);
		if(context != null) {
			return context.variables.get(name);
		}
		
		return Undefined.UNDEFINED;
	}
	
	/**
	 * Shortcut for getValue(final String name)
	 * @param identifier is the variable
	 * @return the value of the variable
	 */
	public Literal getValue(final Identifier identifier) {
		return getValue(identifier.getName());
	}
	
	/**
	 * Sets the value of a variable in the context which defines the variable
	 * @param name is the name of the variable
	 * @param value is the new value which the variable should have
	 */
	public void setValue(final String name, final Literal value) {
		final var context = findContextWithVariable(name);
		if(context != null) {
			context.variables.put(name, value);
		}else {
			variables.put(name, value);
		}
	}
	
	/**
	 * Shortcut for setValue(final String name, final Literal value)
	 * @param identifier is the variable
	 * @param value is the new value which the variable should have
	 */
	public void setValue(final Identifier identifier, final Literal value) {
		setValue(identifier.getName(), value);
	}
	
	/**
	 * Sets the value only in this contexts
	 * @param name is the name of the variable
	 * @param value is the new value which the variable should have
	 */
	public void setOwnValue(final String name, final Literal value) {
		variables.put(name, value);
	}
	/**
	 * Shortcut for setOwnValue(final String name, final Literal value)
	 * @param identifier is the variable
	 * @param value is the new value which the variable should have
	 */
	public void setOwnValue(final Identifier identifier, final Literal value) {
		setOwnValue(identifier.getName(), value);
	}
	
	/**
	 * Convenience method for declaring a variable from type Function which uses the
	 * funtional interface {@link FunctionBridgeBase} as a function body
	 * @param name is the name of the variable
	 * @param body is the body of the function
	 */
	public void declareFunction(final String name, final FunctionBridgeBase body) {
		setValue(name, new FunctionBridge(body));
	}
}
