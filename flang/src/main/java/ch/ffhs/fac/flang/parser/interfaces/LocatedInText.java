package ch.ffhs.fac.flang.parser.interfaces;

import ch.ffhs.fac.flang.parser.Location;

public interface LocatedInText {
	public void setLocation(final Location location);
	public Location getLocation();
}
