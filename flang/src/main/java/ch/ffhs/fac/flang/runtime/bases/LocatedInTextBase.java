package ch.ffhs.fac.flang.runtime.bases;

import ch.ffhs.fac.flang.parser.Location;
import ch.ffhs.fac.flang.parser.interfaces.LocatedInText;

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
