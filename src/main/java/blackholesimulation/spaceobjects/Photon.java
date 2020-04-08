package blackholesimulation.spaceobjects;

import java.util.Objects;
import javafx.geometry.Point2D;

public class Photon {

	/**
	 * <p>
	 * The speed of light {C}.
	 * </p>
	 * <p>
	 * 299.792.458 m/s
	 * </p>
	 */
	public final static double SPEED = 299792458;
	
	private Point2D position;
	
	
	
	public Photon(Point2D position) { this.position = position; }
	
	
	
	@Override
	public String toString() {
		return String.format("Photon [position: %s, speed: %f]", position, SPEED);
	}

	@Override
	public int hashCode() {
		int hash = 3;
		return hash;
	}

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
		return Objects.equals(this.position, other.position);
	}
	
	
	
	// Getters e setters
	
	public Point2D getPosition() { return position; }

	public void setPosition(Point2D position) { this.position = position; }

	public double getSpeed() { return SPEED; }

}
