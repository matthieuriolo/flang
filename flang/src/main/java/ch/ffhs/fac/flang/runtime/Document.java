package ch.ffhs.fac.flang.runtime;

import java.util.LinkedList;
import java.util.List;

public class Document extends Closure {
	public Document(final List<Instruction> instructions) {
		super(instructions);
	}
	
	public Literal execute() {
		return this.execute(this);
	}
}
