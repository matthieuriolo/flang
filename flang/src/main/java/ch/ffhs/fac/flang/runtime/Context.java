package ch.ffhs.fac.flang.runtime;

import java.util.HashMap;
import java.util.Map;

public class Context {
	private final Map<String, Expression> variables = new HashMap<String, Expression>();
	
	public Expression getValue(final String name) {
		return variables.get(name);
	}
	
	public void setValue(final String name, final Expression value) {
		variables.put(name, value);
	}
}
