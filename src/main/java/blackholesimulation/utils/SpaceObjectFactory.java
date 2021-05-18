package blackholesimulation.utils;

import blackholesimulation.enums.SpaceBodies;
import blackholesimulation.space.SpaceObject;
import blackholesimulation.spaceobjects.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import java.util.concurrent.ThreadLocalRandom;

public class SpaceObjectFactory {
    private Circle view;
    private SpaceObject spaceObject;


    public SpaceObjectFactory(SpaceBodies type) {
        createSpaceBody(type);
    }


    public Circle getSpaceObjectView() {
        return view;
    }

    public SpaceObject getSpaceObject() {
        return spaceObject;
    }


    // ========== HELPER METHODS ==========

    private void createSpaceBody(SpaceBodies type) {
        if (type == SpaceBodies.ASTEROID) {
            createAsteroid();
        } else if (type == SpaceBodies.PLANET) {
            createPlanet();
        } else if (type == SpaceBodies.STAR) {
            createStar();
        } else if (type == SpaceBodies.WHITE_DWARF) {
            createWhiteDwarf();
        } else {
            createPhotons();
        }
    }

    // === SpaceBodies

    private void createAsteroid() {
        view = new Circle(20, 650, 15, Color.web("#514531"));
        spaceObject = new Asteroid(new Point2D(view.getCenterX(), view.getCenterY()), new Point2D(2, 2), 100);
    }

    private void createPlanet() {
        view = new Circle(20, 380, 20, Color.web("#836FFF"));
        spaceObject = new Planet(new Point2D(view.getCenterX(), view.getCenterY()), new Point2D(2, 2), 400);
    }

    private void createStar() {
        view = new Circle(20, 350, 40, Color.web("#B8860B"));
        spaceObject = new Star(new Point2D(view.getCenterX(), view.getCenterY()), new Point2D(2, 2), 2000);
    }

    private void createWhiteDwarf() {
        RadialGradient gradient = new RadialGradient(
                0,
                0,
                0.5037036895751953,
                0.4873015630812872,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.WHITE), new Stop(0.5110795117904966, Color.web("#fffcfc")),
                new Stop(1, Color.web("#cccccc"))
        );
        view = new Circle(20, 600, 25, gradient);
        view.setStroke(Color.web("#d4d4d4"));

        spaceObject = new WhiteDwarf(new Point2D(view.getCenterX(), view.getCenterY()), new Point2D(2, 2), 2000);
    }

    private void createPhotons() {
        view = new Circle(50, ThreadLocalRandom.current().nextDouble(380, 750), 2.5, Color.web("#F0FFFF"));
        spaceObject = new Photon(new Point2D(view.getCenterX(), view.getCenterY()));
    }

}
