package ch.ffhs.fac.flang.runtime;

public interface Visitable {
	public void acceptVisitor(Visitor visitor);
}
