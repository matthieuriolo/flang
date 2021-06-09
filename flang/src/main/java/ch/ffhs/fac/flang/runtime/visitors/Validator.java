package ch.ffhs.fac.flang.runtime.visitors;

import java.util.HashSet;

import ch.ffhs.fac.flang.runtime.literals.Function;

public class Validator extends Transferer {
	@Override
	public void visitLiteralFunction(Function obj) {
		final var args = new HashSet<String>();
		for(final var argument : obj.getParameters()) {
			if(!args.add(argument.getName())) {
				throw new RuntimeException("The argument '" + argument.getName() + "' has been declared multiple times");
			}
		}
		super.visitLiteralFunction(obj);
	}
}
