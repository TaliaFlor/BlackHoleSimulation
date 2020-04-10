package blackholesimulation.space;

import blackholesimulation.spaceobjects.Photon;
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
	
	/**
	 * <p>
	 * The universal gravitational constant (<b>G</b>) is a physical constant
	 * involved in the calculation of gravitational effects in Newton's law of
	 * universal gravitation and in Albert Einstein's general theory of relativity.
	 * </p>
	 * 
	 * <blockquote>
	 * <p>
	 * Real value:
	 * {@code 6.67408 × 10^(-11) m^3/(kg * s^2) ---> 0.0000000000667408 m^3/(kg * s^2)}
	 * </p>
	 * <blockquote>
	 * 
	 */
	// public static final double UNIVERSAL_GRAVITATIONAL_CONSTANT =
	// 6.67408*Math.pow(10, -11);
	public static final double UNIVERSAL_GRAVITATIONAL_CONSTANT = 3.54;
	private static final double DELTA_TIME = 0.1;
	

	private Point2D position;
	private double mass;
	/**
	 * <p>
	 * The Schwarzschild radius i.e. the radius of the event horizon
	 * </p>
	 *  <blockquote>
	 * <p>
	 * Formula: {@code (2 × G × m) / c^2 meters}
	 * </p>
	 * <p>
	 * Simplified for the standards values: {@code m × 1.48428 × 10^(-27) meters}
	 * </p>
	 * <blockquote>
	 */
	private double rs;
        private double accretionDisk;
	
	

	/**
	 * <p>
	 * Creates a black hole on the origin (position (0, 0)) and mass of 100.000 kg
	 * </p>
	 */
	public BlackHole() { this(new Point2D(0, 0), 100000); }

	/**
	 * <p>
	 * Creates a new black hole on the position (0, 0).
	 * </p>
	 * 
	 * @param mass the mass of the black hole (measured in kilograms)
	 */
	public BlackHole(double mass) { this(new Point2D(0, 0), mass); }

	public BlackHole(double x, double y, double mass) { this(new Point2D(x, y), mass); }

	public BlackHole(Point2D position, double mass) {
		this.position = position;
		this.mass = mass;
//		this.rs = mass * 1.48428 * Math.pow(10, -27); // metros
		this.rs = (2 * UNIVERSAL_GRAVITATIONAL_CONSTANT * mass) / Math.pow(Photon.SPEED_OF_LIGHT, 2); // metros
                this.accretionDisk = this.rs * 3;
	}
	
	

	@Override
	public String toString() {
		return String.format("BlackHole [position: %s, mass: %f, rs: %f]", position, mass, rs);
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
	
	

	public boolean pull(MassiveBody body) {

		/*
		 * 
		 * p5.Vector.sub(this.pos, photon.pos); const theta = force.heading(); const r =
		 * force.mag(); const fg = G * this.mass / (r * r);
		 * 
		 * let deltaTheta = -fg * (dt / c) * sin(photon.theta - theta); deltaTheta /=
		 * abs(1.0 - 2.0 * G * this.mass / (r * c * c)); photon.theta += deltaTheta;
		 * 
		 * photon.vel = p5.Vector.fromAngle(photon.theta);
		 * 
		 * photon.vel.setMag(c); -- ta faltando
		 */

		Point2D force = body.getPosition().subtract(this.position); // Vetor de direção do buraco negro
		double direction = force.angle(this.position);
		double displacement = force.magnitude(); // r
		double gravitationalForce = (UNIVERSAL_GRAVITATIONAL_CONSTANT * this.mass) / (displacement * displacement);

		double bodyDirection = body.getPosition().subtract(this.position).angle(body.getPosition());
		double deltaDirection = (-gravitationalForce * (DELTA_TIME / Photon.SPEED_OF_LIGHT)
				* Math.sin(bodyDirection - direction)); // a direção que o vetor a ponta, formando um entre o x
														// caartesiano e o vetor princiapl

		deltaDirection /= Math.abs(Math.pow(displacement, -1) - this.rs);

		double bodyMagnitude = body.getPosition().subtract(this.position).magnitude();
		bodyDirection = bodyDirection + deltaDirection;
		body.setSpeed(createVector(bodyDirection, bodyMagnitude));
		// body.setPosition(body.getSpeed());
		body.setPosition(body.getPosition().subtract(body.getSpeed()));
		System.out.println("body position:" + body.getPosition());
		System.out.println("vel: " + body.getSpeed());
                System.out.printf("body Direction: %.4f \n",bodyDirection);
		System.out.println();
		System.out.println("black hole position: " + this.position);
		System.out.println("black hole mass: " + this.mass);
		System.out.println();

		System.out.println("black hole distance: " + (this.position.distance(body.getPosition())));
                System.out.println("event horizon: " + this.rs);

		System.out.println("-=-=-=-=-=-=-=-=-=-=-==--=-=-=-=-=");

		if (displacement <= this.rs + 0.5) {
			System.out.println("STOP");
			return true;
		}
                if(Double.isInfinite(this.position.distance(body.getPosition()))){
                    System.out.println("escapou");
                    return true;
                }
		return false;
	}

	private Point2D createVector(double angle, double magnitude) {
		double x = (Math.cos(angle) * magnitude);
		double y = (Math.sin(angle) * magnitude);

		return new Point2D(x, y);

	}

	public double distance(MassiveBody body) {
		/*
		 * distancia = Math.pow(jogadorY - inimigoY,2) + Math.pow(jogadorX -
		 * inimigoX,2);
		 * 
		 * distancia = Math.sqrt(distancia);
		 * 
		 * if(distancia <= jogadorRaio + inimigoRaio){ fimJogo = true; } }
		 */

		double distance = Math.pow(body.getPosition().getX() - this.position.getX(), 2)
				+ Math.pow(body.getPosition().getY() - this.position.getY(), 2);
		distance = Math.sqrt(distance);
		System.out.println("distance: " + distance);
		/*
		 * if(distance <= this.rs + 1000){
		 * System.out.println("sugado pelo buraco negro"); return true; } return false;
		 */
		return distance;
	}
	

	public double getDeltaTime() { return DELTA_TIME; }

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
