package blackholesimulation.spaceobjects;

import java.util.Objects;
import javafx.geometry.Point2D;

/**
 * <p>
 * Representation of a photon of light on a 2D space.
 * </p>
 */
public class Photon {

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
	public final static double SPEED_OF_LIGHT = 300; // Arredondar para 30 ou 300 milhões m/s

	private Point2D position;
	
	
	/**
	 * <p>
	 * Creates a new photon at a given position.
	 * </p>
	 * 
	 * @param position position in space of the photon
	 */
	public Photon(Point2D position) {
		this.position = position;
	}
	
	

	@Override
	public String toString() {
		return String.format("Photon [position: %s]", position);
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

	public double getSpeed() { return SPEED_OF_LIGHT; }

}
