package blackhole.simulation.space;

import javafx.geometry.Point2D;

/**
 * 
 * <p>
 * A represantation of a Schwarzschild black hole or static black hole, in other
 * words, a black hole that has neither electric charge nor angular momentum. A
 * Schwarzschild black hole is described by the Schwarzschild metric, and cannot
 * be distinguished from any other Schwarzschild black hole except by its mass.
 * </p>
 *
 */
public class BlackHole {

	private Point2D position;
	private double mass;
	/**
	 * <p>
	 * The Schwarzschild radius i.e. the radius of the event horizon
	 * </p>
	 */
	private double rs;

	
	
	/**
	 * <p>
	 * Creates a black hole on the origin (position (0, 0)) and mass of 100.000 kg
	 * </p>
	 */
	public BlackHole() {
		this(new Point2D(0, 0), 100000);
	}

	/**
	 * <p>
	 * Creates a new black hole on the position (0, 0).
	 * </p>
	 * 
	 * @param mass the mass of the black hole (measured in kilograms)
	 */
	public BlackHole(double mass) {
		this(new Point2D(0, 0), mass);
	}

	public BlackHole(double x, double y, double mass) {
		this(new Point2D(x, y), mass);
	}

	public BlackHole(Point2D position, double mass) {
		this.position = position;
		this.mass = mass;
		this.rs = mass * 1.48428 * Math.pow(10, -27); // metros
	}
	
	

	@Override
	public String toString() {
		return String.format("BlackHole [position: %s, mass: %s, rs: %s]", position, mass, rs);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(rs);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BlackHole)) {
			return false;
		}
		BlackHole other = (BlackHole) obj;
		return mass == other.mass && rs == other.rs;
	}
	
	
	
	public void pull(Object object) {
		
		// TODO: implementation
		
	}
	
	public double getPhotonSphere() { return rs * 1.5; }

	/**
	 * <p>
	 * ISCO or innermost radius of the accreation disk
	 * </p>
	 * 
	 * @return the ISCO value
	 */
	public double getInnermostStableCircularOrbit() { return rs * 3; }
	
	public double getPhotonScapeDistance() { return rs * 2.6; }
	

	// Getters e setters

	public Point2D getPosition() { return position; }

	public void setPosition(Point2D position) { this.position = position; }

	public double getMass() { return mass; }

	public void setMass(double mass) { this.mass = mass; }

	public double getRs() { return rs; }

	public void setRs(double rs) { this.rs = rs; }

}