package blackholesimulation.space;

import javafx.geometry.Point2D;

import java.util.Objects;

public abstract class SpaceObject {

    private Point2D position;
    private Point2D velocity;


    public SpaceObject(Point2D position, Point2D speed) {
        this.position = position;
        this.velocity = speed;
    }


    @Override
    public String toString() {
        return String.format("SpaceObject [position: %s, velocity: %s]", position, velocity);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.position);
        hash = 79 * hash + Objects.hashCode(this.velocity);
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
        final SpaceObject other = (SpaceObject) obj;
        return Objects.equals(position, other.position) && Objects.equals(velocity, other.velocity);
    }


    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D speed) {
        this.velocity = speed;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

}
