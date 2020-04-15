
package blackholesimulation.spaceobjects;

import java.util.Objects;

import blackholesimulation.space.SpaceObject;
import javafx.geometry.Point2D;

/**
 * <p>
 * Representation of a photon of light on a 2D space.
 * </p>
 */
public class Photon extends SpaceObject {

	/**
	 * <p>
	 * The speed of light (<b>c</b>) is the speed at which light travels in a vacumn.
	 * </p>
	 * 
	 * <blockquote>
	 * <p>
	 * Real velocity: {@code 299.792.458 m/s}
	 * </p>
	 * <p>
	 * Normalized velocity: {@code 300 millions m/s}
	 * </p>
	 * <blockquote>
	 * 
	 */
//	public final static double SPEED_OF_LIGHT = 299792458;
	public final static double SPEED_OF_LIGHT = 300; // Round to 30 or 300 millions m/s

	
	
	
	/**
	 * <p>
	 * Creates a new photon at a given position.
	 * </p>
	 * 
	 * @param position position in space of the photon
	 */
	public Photon(Point2D position) {
		super(position, new Point2D(SPEED_OF_LIGHT, SPEED_OF_LIGHT));
	}
	
	

	@Override
	public String toString() {
		return String.format("Photon [position: %s]", getPosition());
	}

	@Override
	public int hashCode() { return 3; }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Photon other = (Photon) obj;
		return Objects.equals(this.getPosition(), other.getPosition());
	}

}