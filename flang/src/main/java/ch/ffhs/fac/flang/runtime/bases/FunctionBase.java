package ch.ffhs.fac.flang.runtime.bases;

import java.util.LinkedList;
import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.LocatedInText;
import ch.ffhs.fac.flang.runtime.Context;

public abstract class FunctionBase implements LocatedInText {
	private final Expression subject;
	private final List<Expression> arguments;
	private Location location;
	
	public FunctionBase(final Expression subject) {
		this(subject, List.of());
	}
	
	public FunctionBase(final Expression subject, final List<Expression> arguments) {
		this.subject = subject;
		this.arguments = arguments;
	}
	
	public Expression getSubject() {
		return subject;
	}

	public List<Expression> getArguments() {
		return arguments;
	}
	
	@Override
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public Location getLocation() {
		return location;
	}

	public Literal perform(Context closure)  throws Throwable {
		final var literal = subject.compute(closure);
		final var parameters = new LinkedList<Literal>();
		for(final var arg : arguments) {
			parameters.add(arg.compute(closure));
		}
		return literal.functionalCall(closure, parameters, location);
	}
}
