package ch.ffhs.fac.flang.runtime;

import java.util.LinkedList;
import java.util.List;

public class Routine {
	private final Expression subject;
	private final List<Expression> arguments;
	
	public Routine(final Expression subject) {
		this(subject, List.of());
	}
	
	public Routine(final Expression subject, final List<Expression> arguments) {
		this.subject = subject;
		this.arguments = arguments;
	}

	public Literal perform(Closure closure)  throws Throwable {
		final var literal = subject.compute(closure);
		final var parameters = new LinkedList<Literal>();
		for(final var arg : arguments) {
			parameters.add(arg.compute(closure));
		}
		return literal.functionalCall(closure, parameters);
	}
}
