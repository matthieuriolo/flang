package ch.ffhs.fac.flang.runtime.visitors;

import java.util.LinkedList;
import java.util.List;

import ch.ffhs.fac.flang.parser.interfaces.Visitor;
import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.bases.VisitorBase;
import ch.ffhs.fac.flang.runtime.visitors.validators.DeadCode;
import ch.ffhs.fac.flang.runtime.visitors.validators.DuplicateArgument;

public class Validator extends VisitorBase {
	@SuppressWarnings("serial")
	private final List<Visitor> validators = new LinkedList<Visitor>() {{
		add(new DeadCode());
		add(new DuplicateArgument());
	}};
	
	
	@Override
	public void visitDocument(final Document doc) {
		validators.stream().forEach(validator -> validator.visitDocument(doc));
	}
}
