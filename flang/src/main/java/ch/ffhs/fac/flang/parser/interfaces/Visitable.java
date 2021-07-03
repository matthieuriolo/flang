package ch.ffhs.fac.flang.parser.interfaces;

/**
 * Interface for structure elements from the parser which can be visited by the {@link Visitor}
 * @author matthieuriolo
 *
 */
public interface Visitable {
	/**
	 * The accept method for the visitor pattern
	 * @param visitor {@link Visitor}
	 */
	public void acceptVisitor(Visitor visitor);
}
