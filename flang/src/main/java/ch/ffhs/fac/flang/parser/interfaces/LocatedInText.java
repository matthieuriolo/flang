package ch.ffhs.fac.flang.parser.interfaces;

import ch.ffhs.fac.flang.parser.Location;
/**
 * Interface for setting the {@link Location} for a returned structure element from the parser
 * @author matthieuriolo
 *
 */
public interface LocatedInText {
	/**
	 * Sets the position in which the structure element was found
	 * @param location {@link Location}
	 */
	public void setLocation(final Location location);
	/**
	 * Returns the location in which the structure element was found
	 * @return {@link Location}
	 */
	public Location getLocation();
}
