package blackholesimulation.space;

import javafx.geometry.Point2D;

import java.util.Objects;

public abstract class MassiveBody extends SpaceObject {

    private double mass;


    public MassiveBody(Point2D position, Point2D speed, double mass) {
        super(position, speed);

        this.mass = mass;
    }


    @Override
    public String toString() {
        return String.format("MassiveBody [position: %s, speed: %s, mass: %f]", getPosition(), getVelocity(), mass);
    }

    @Override
    public int hashCode() {
        return 7;
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
        MassiveBody other = (MassiveBody) obj;
        return mass == other.mass && Objects.equals(this.getVelocity(), other.getVelocity());
    }


    // Getters e setters

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

}