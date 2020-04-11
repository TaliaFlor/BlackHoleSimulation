package blackholesimulation.space;

import javafx.geometry.Point2D;

import java.util.Objects;

public abstract class MassiveBody {

    private Point2D position;
    private Point2D speed;
    private double mass;
    


    public MassiveBody(Point2D position, Point2D speed, double mass) {
        this.position = position;
        this.speed = speed;
        this.mass = mass;
    }

    

    @Override
    public String toString() {
        return String.format("MassiveBody [position: %s, speed: %s, mass: %f]", position, speed, mass);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        MassiveBody other = (MassiveBody) obj;
        return mass == other.mass && Objects.equals(this.speed, other.speed);
    }
    


    // Getters e setters

    public Point2D getPosition() { return position; }

    public void setPosition(Point2D position) { this.position = position; }

    public Point2D getSpeed() { return speed; }

    public void setSpeed(Point2D speed) { this.speed = speed; }

    public double getMass() { return mass; }

    public void setMass(double mass) { this.mass = mass; }

}