package ch.ffhs.fac.flang.runtime;

import java.util.LinkedList;
import java.util.List;

public class Closure {
	private final Closure parent;
	private final List<Instruction> instructions = new LinkedList<Instruction>();
	
	public Closure() {
		this(null);
	}
	
	public Closure(final Closure parent) {
		this.parent = parent;
	}
	
	public void addInstruction(final Instruction instr) {
		instructions.add(instr);
	}
	
	public Closure getParentClosure() {
		return parent;
	}
	
	public void execute(final Context ctx) {
		
	}
}
