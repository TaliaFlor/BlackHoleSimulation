package blackholesimulation.space;

import blackholesimulation.spaceobjects.Photon;
import javafx.geometry.Point2D;

/**
 * <p>
 * A represantation of a Schwarzschild black hole or static black hole, in other
 * words, a black hole that has neither electric charge nor angular momentum. A
 * Schwarzschild black hole is described by the Schwarzschild metric, and cannot
 * be distinguished from any other Schwarzschild black hole except by its mass.
 * </p>
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
     * {@code 6.67408 * 10^(-11) m^3/(kg * s^2) ---> 0.0000000000667408 m^3/(kg * s^2)}
     * </p>
     * <blockquote>
     */
    // public static final double UNIVERSAL_GRAVITATIONAL_CONSTANT = 6.67408 * Math.pow(10, -11);
    public static final double UNIVERSAL_GRAVITATIONAL_CONSTANT = 3.54;
    private static final double DELTA_TIME = 0.1;

    private Point2D position;
    private double mass;
    /**
     * <p>
     * The Schwarzschild radius i.e. the radius of the event horizon
     * </p>
     * <blockquote>
     * <p>
     * Formula: {@code (2 * G * m) / c^2 meters}
     * </p>
     * <p>
     * Simplified for the standards values: {@code m * 1.48428 * 10^(-27) meters}
     * </p>
     * <blockquote>
     */
    private double rs;


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

        Point2D force = body.getPosition().subtract(this.position); // Black hole direction vector
        double direction = force.angle(this.position);
        double displacement = force.magnitude();
        double gravitationalForce = (UNIVERSAL_GRAVITATIONAL_CONSTANT * this.mass) / (displacement * displacement);

        double bodyDirection = body.getPosition().subtract(this.position).angle(body.getPosition());
        double deltaDirection = -(gravitationalForce * DELTA_TIME
                * Math.sin(bodyDirection - direction)) / Photon.SPEED_OF_LIGHT; // The direction the vector points, i.e., the angle formed
        // between the Cartesian X and the vector itself

        deltaDirection /= Math.abs(Math.pow(displacement, -1) - this.rs);

        double bodyMagnitude = body.getPosition().subtract(this.position).magnitude();
        bodyDirection = bodyDirection + deltaDirection;
        body.setSpeed(createVectorFromAngle(bodyDirection, bodyMagnitude));
        body.setPosition(body.getPosition().subtract(body.getSpeed()));

        // Console output
        System.out.println("body position:" + body.getPosition());
        System.out.println("vel: " + body.getSpeed());
        System.out.printf("body Direction: %.4f \n", bodyDirection);
        System.out.println();
        System.out.println("black hole position: " + this.position);
        System.out.println("black hole mass: " + this.mass);
        System.out.println();

        System.out.println("black hole distance: " + (this.position.distance(body.getPosition())));
        System.out.println("event horizon: " + this.rs);

        System.out.println("-=-=-=-=-=-=-=-=-=-=-==--=-=-=-=-=");

        // Stop conditions
        if (displacement <= this.rs + 0.5) {
            System.out.println("Fell into the black hole");
            return true;
        }
        if (Double.isInfinite(this.position.distance(body.getPosition()))) {
            System.out.println("Escaped to infinity");
            return true;
        }

        return false;
    }
    

    /**
     * <p>
     * Creates a new Point2D object from a given angle and magnitude. Equivalent to,
     * if it existed, {@code new Point2D(angle, magnitude)}.
     * </p>
     *
     * @param angle     the angle of the new vector
     * @param magnitude the magnitude of the vector
     * @return a new Point2D object with the given angle and magnitude
     */
    private Point2D createVectorFromAngle(double angle, double magnitude) {
        double x = Math.cos(angle) * magnitude;
        double y = Math.sin(angle) * magnitude;

        return new Point2D(x, y);
    }

    public double getDeltaTime() { return DELTA_TIME; }

    

    // Black hole structures

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