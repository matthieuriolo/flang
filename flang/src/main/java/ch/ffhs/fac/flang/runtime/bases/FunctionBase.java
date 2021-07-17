package ch.ffhs.fac.flang.runtime.bases;

import java.util.LinkedList;
import java.util.List;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.Expression;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.parser.interfaces.LocatedInText;
import ch.ffhs.fac.flang.runtime.Context;
/**
 * Base class for the expression {@link FunctionCall} and the instruction  {@link ProdecureCall}
 * @author matthieuriolo
 *
 */
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
	
	/**
	 * Getter for the subject
	 * @return
	 */
	public Expression getSubject() {
		return subject;
	}

	/**
	 * Getter for the argument list
	 * @return
	 */
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

	/**
	 * Executes the function call and returns the resulting {@link Literal}
	 * @param context the scope in which the function call is located in
	 * @return the resulting {@link Literal}
	 * @throws Throwable
	 */
	public Literal perform(final Context context)  throws Throwable {
		final var literal = subject.compute(context);
		final var parameters = new LinkedList<Literal>();
		for(final var arg : arguments) {
			parameters.add(arg.compute(context));
		}
		return literal.functionalCall(context, parameters);
	}
}
