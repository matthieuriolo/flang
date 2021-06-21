package ch.ffhs.fac.flang.runtime.visitors.validators;

import java.util.HashSet;

import ch.ffhs.fac.flang.runtime.bases.VisitorBase;
import ch.ffhs.fac.flang.runtime.literals.Function;

public class DuplicateArgument extends VisitorBase {
	@Override
	public void visitLiteralFunction(final Function obj) {
		final var args = new HashSet<String>();
		for(final var argument : obj.getParameters()) {
			if(!args.add(argument.getName())) {
				// TODO
				throw new RuntimeException("The argument '" + argument.getName() + "' has been declared multiple times");
			}
		}
		super.visitLiteralFunction(obj);
	}
}
