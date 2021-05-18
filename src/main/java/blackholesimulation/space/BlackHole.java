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
     * Creates a new black hole on the position (0, 0).
     * </p>
     *
     * @param mass the mass of the black hole (measured in kilograms)
     */
    public BlackHole(double mass) {
        this(new Point2D(0, 0), mass);
    }

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


    public boolean pull(SpaceObject spaceObject) {
        Point2D force = position.subtract(spaceObject.getPosition());    // Black hole direction vector
        double direction = force.angle(this.position) * (Math.PI / 180); // direction vector on radians
        double displacement = force.magnitude();
        double gravitationalForce = (UNIVERSAL_GRAVITATIONAL_CONSTANT * this.mass) / (displacement * displacement);

//		double spaceObjectDirection = spaceObject.getPosition().subtract(this.position)
//				.angle(spaceObject.getPosition())  * (Math.PI / 180);
        double spaceObjectDirection = spaceObject.getPosition().subtract(this.position)
                .angle(spaceObject.getPosition());
        double deltaDirection = -(gravitationalForce * DELTA_TIME * Math.sin(spaceObjectDirection - direction))
                / Photon.SPEED_OF_LIGHT; // The direction the vector points

//		deltaDirection /= Math.abs(1 - ((2 * UNIVERSAL_GRAVITATIONAL_CONSTANT * mass) / (displacement * Math.pow(Photon.SPEED_OF_LIGHT, 2))));
        deltaDirection /= Math.abs(1 - (rs / displacement));

        spaceObjectDirection += deltaDirection;

        double spaceObjectMagnitude = spaceObject.getPosition().subtract(this.position).magnitude();
        spaceObject.setVelocity(createVectorFromAngle(spaceObjectDirection, spaceObjectMagnitude));
        spaceObject.setPosition(spaceObject.getPosition().subtract(spaceObject.getVelocity()));

        // Mantains photon speed constant
        if (spaceObject instanceof Photon) {
            spaceObject.setVelocity(new Point2D(Photon.SPEED_OF_LIGHT, Photon.SPEED_OF_LIGHT));
        }

        // consoleOutput(spaceObject, spaceObjectDirection);

        return stopped(spaceObject);
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

    /**
     * <p>
     * Write output to console.
     * </p>
     *
     * @param spaceObject          the SpaceObject instance
     * @param spaceObjectDirection the direction in which the SpaceObject is going
     */
    private void consoleOutput(SpaceObject spaceObject, double spaceObjectDirection) {
        String console = "-=-=-=-=-=-=-=-=-=-=-==--=-=-=-=-=-\n"
                + "Black hole:\n"
                + "\t- Mass: " + this.mass + "\n"
                + "\t- Position: " + this.position + "\n"
                + "SpaceObject:\n"
                + "\t- Position: " + spaceObject.getPosition() + "\n"
                + "\t- Velocity: " + spaceObject.getVelocity() + "\n"
                + "\t- Direction: " + String.format("%.4f\n", spaceObjectDirection)
                + "Result:\n"
                + "\t- Black hole event horizon: " + this.rs + "\n"
                + "\t- Distance from the black hole: " + (this.position.distance(spaceObject.getPosition())) + "\n"
                + "-=-=-=-=-=-=-=-=-=-=-==--=-=-=-=-=-";
        System.out.println(console);
    }

    /**
     * <p>
     * Wheter the conditions to stop the calculations have been reached.
     * </p>
     *
     * @param spaceObject
     * @return whether the object has stopped or not
     */
    private boolean stopped(SpaceObject spaceObject) {
        if (this.position.distance(spaceObject.getPosition()) < this.rs) {
//			System.out.println("Fell into the black hole");
            return true;
        }
        //			System.out.println("Escaped to infinity");
        return Double.isInfinite(this.position.distance(spaceObject.getPosition()));
    }

    public double getDeltaTime() {
        return DELTA_TIME;
    }


    // Black hole structures

    public double getPhotonSphere() {
        return rs * 1.5;
    }

    /**
     * <p>
     * ISCO or innermost radius of the accreation disk
     * </p>
     *
     * @return the ISCO value
     */
    public double getInnermostStableCircularOrbit() {
        return rs * 3;
    }

    public double getPhotonScapeDistance() {
        return rs * 2.6;
    }


    // Getters e setters

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRs() {
        return rs;
    }

    public void setRs(double rs) {
        this.rs = rs;
    }

}