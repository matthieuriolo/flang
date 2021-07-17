package ch.ffhs.fac.flang.runtime.visitors.validators;

import java.util.HashSet;

import ch.ffhs.fac.flang.runtime.bases.VisitorBase;
import ch.ffhs.fac.flang.runtime.exceptions.DuplicatedArgumentException;
import ch.ffhs.fac.flang.runtime.literals.Function;
/**
 * Validator class which tests if the parameter list in a function does not contain the same parameter twice
 * @author matthieuriolo
 *
 */
public class DuplicateArgument extends VisitorBase {
	@Override
	public void visitLiteralFunction(final Function obj) {
		final var args = new HashSet<String>();
		for(final var argument : obj.getParameters()) {
			if(!args.add(argument.getName())) {
				throw new DuplicatedArgumentException(argument);
			}
		}
		super.visitLiteralFunction(obj);
	}
}
