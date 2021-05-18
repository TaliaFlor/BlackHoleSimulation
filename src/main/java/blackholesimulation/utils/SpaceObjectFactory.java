package blackholesimulation.utils;

import java.util.concurrent.ThreadLocalRandom;

import blackholesimulation.enums.SpaceBodies;
import blackholesimulation.space.SpaceObject;
import blackholesimulation.spaceobjects.Asteroid;
import blackholesimulation.spaceobjects.Photon;
import blackholesimulation.spaceobjects.Planet;
import blackholesimulation.spaceobjects.Star;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpaceObjectFactory {

	public static Circle getSpaceObjectView(SpaceBodies type) {
		Circle view = null;
		if (type == SpaceBodies.ASTEROID) {
			view = new Circle(20, 650, 15, Color.web("#514531"));
		} else if (type == SpaceBodies.PLANET) {
			view = new Circle(20, 380, 20, Color.web("#836FFF"));
		} else if (type == SpaceBodies.STAR) {
			view = new Circle(20, 350, 40, Color.web("#B8860B"));
		} else {	// Photon
			view = new Circle(50, ThreadLocalRandom.current().nextDouble(380, 750), 2.5, Color.web("#F0FFFF"));
		}
		return view;
	}

	public static SpaceObject getSpaceObject(Circle view, SpaceBodies type) {
		SpaceObject spaceObject = null;
		if (type == SpaceBodies.ASTEROID) {
			spaceObject = new Asteroid(new Point2D(view.getCenterX(), view.getCenterY()), new Point2D(2, 2), 100);
		} else if (type == SpaceBodies.PLANET) {
			spaceObject = new Planet(new Point2D(view.getCenterX(), view.getCenterY()), new Point2D(2, 2), 400);
		} else if (type == SpaceBodies.STAR) {
			spaceObject = new Star(new Point2D(view.getCenterX(), view.getCenterY()), new Point2D(2, 2), 2000);
		} else {	// Photon
			spaceObject = new Photon(new Point2D(view.getCenterX(), view.getCenterY()));
		}
		return spaceObject;
	}

}
