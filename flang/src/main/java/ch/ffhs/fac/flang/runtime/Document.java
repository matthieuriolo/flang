package ch.ffhs.fac.flang.runtime;

public class Document extends Closure {
	public void execute() {
		this.execute(this);
	}
}
