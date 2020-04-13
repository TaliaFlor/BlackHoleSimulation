
package blackholesimulation.space;

import java.util.Objects;
import javafx.geometry.Point2D;

public abstract class SpaceObject {

	private Point2D position;
	private Point2D speed;
	
	

	public SpaceObject(Point2D position, Point2D speed) {
		this.position = position;
		this.speed = speed;
	}
	
	
	
	@Override
	public String toString() {
		return String.format("SpaceObject [position: %s, speed: %s]", position, speed);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + Objects.hashCode(this.position);
		hash = 79 * hash + Objects.hashCode(this.speed);
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
		return Objects.equals(position, other.position) && Objects.equals(speed, other.speed);
	}
	
	

	public Point2D getSpeed() { return speed; }

	public void setSpeed(Point2D speed) { this.speed = speed; }

	public Point2D getPosition() { return position; }

	public void setPosition(Point2D position) { this.position = position; }

}
