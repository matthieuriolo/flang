package ch.ffhs.fac.flang.runtime.visitors;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import ch.ffhs.fac.flang.runtime.literals.Function;
import ch.ffhs.fac.flang.runtime.literals.Identifier;

public class Validator extends Transferer {
	// TODO
	@SuppressWarnings("serial")
	final Set<String> protectedKeywords = new HashSet<String>() {{
		add("true");
		add("false");
		add("undefined");
		add("and");
		add("or");
		add("__arguments__");
	}};
	
	@Override
	public void visitLiteralFunction(final Function obj) {
		final var args = new HashSet<String>();
		for(final var argument : obj.getParameters()) {
			if(!args.add(argument.getName())) {
				// TODO
				throw new RuntimeException("The argument '" + argument.getName() + "' has been declared multiple times");
			}
			
			if(protectedKeywords.contains(argument.getName().toLowerCase())) {
				// TODO
				throw new RuntimeException("Protected keyword!");
			}
		}
		super.visitLiteralFunction(obj);
	}
	
	@Override
	public void visitLiteralIdentifier(final Identifier identifier) {
		if(protectedKeywords.contains(identifier.getName().toLowerCase())) {
			// TODO
			throw new RuntimeException("Protected keyword!");
		}
	}
}
