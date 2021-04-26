package ch.ffhs.fac.flang.runtime;

import java.util.List;

public class Document extends Closure {
	public Document(final List<Instruction> instructions) {
		super(instructions);
	}
}
