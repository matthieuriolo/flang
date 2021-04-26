package ch.ffhs.fac.flang.runtime;

public class Document extends Closure {
	public Literal execute() {
		return this.execute(this);
	}
}
