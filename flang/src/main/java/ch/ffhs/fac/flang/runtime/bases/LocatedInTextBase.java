package ch.ffhs.fac.flang.runtime.bases;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.LocatedInText;

/**
 * Abstract class which implements the {@link LocatedInText} interface
 * 
 * Literals, expression and instructions inherit those methods and can inform the user at which location an
 * error has been
 * @author matthieuriolo
 *
 */
public abstract class LocatedInTextBase implements LocatedInText {
	private Location location;
	
	@Override
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public Location getLocation() {
		return location;
	}
}
