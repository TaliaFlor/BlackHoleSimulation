package blackholesimulation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import blackholesimulation.enums.SpaceBodies;
import blackholesimulation.space.BlackHole;
import blackholesimulation.space.SpaceObject;
import blackholesimulation.spaceobjects.Asteroid;
import blackholesimulation.spaceobjects.Photon;
import blackholesimulation.spaceobjects.Planet;
import blackholesimulation.spaceobjects.Star;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimulationController implements Initializable {

	private final BlackHole blackHole = new BlackHole(Math.pow(9.5, 6));

	private final SpaceObject[] spaceObjectArray = new SpaceObject[100];
	private final Circle[] spaceObjectViewArray = new Circle[100];

	private final SpaceBodies spaceBody;
	private SpaceObject spaceObject;
	private Circle spaceObjectView;

	private Circle blackCircle;

	@FXML
	private AnchorPane root;
	@FXML
	private Ellipse accretionDisk;
	@FXML
	private Ellipse photonSphere;
	@FXML
	private Circle eventHorizon;
	
	

	public SimulationController(SpaceBodies spaceBody) {
		this.spaceBody = spaceBody;
	}
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Draw nodes on screen
		if (spaceBody == SpaceBodies.ASTEROID) {
			showAsteroid();
		} else if (spaceBody == SpaceBodies.PLANET) {
			showPlanet();
		} else if (spaceBody == SpaceBodies.STAR) {
			showStar();
		} else {
			showPhotons();
		} 
		showBlackHole();
		showBlackCircle();

		// Animate nodes
		if (spaceBody != SpaceBodies.PHOTON) {
			pullObject();
		} else {
			pullPhotons();
		}

	}
	
	

	// Button

	@FXML
	private void back(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../view/fxmls/OptionsMenu_View.fxml"));

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		// Load fxml on scene
		stage.setTitle("Options Menu");
		stage.setScene(new Scene(fxmlloader.load()));
		stage.show();
	}
	

	// Animation
	@FXML
	private void pullObject() {
		boolean goInfinity = false;
		boolean beyondScreen = false;
		while (!goInfinity && !beyondScreen) {
			goInfinity = blackHole.pull(spaceObject);
			beyondScreen = animation(spaceObjectView, spaceObject);
		}
	}

	private void pullPhotons() {
		boolean goInfinity = false;
		boolean beyondScreen = false;
		boolean breakLoop = false;
		while (!goInfinity && !beyondScreen) {
			for (int i = 0; i < spaceObjectViewArray.length; i++) {
				if (spaceObjectViewArray[i] == null) {
					breakLoop = true;
					break;
				}
				goInfinity = blackHole.pull(spaceObjectArray[i]);
				beyondScreen = animation(spaceObjectViewArray[i], spaceObjectArray[i]);
			}
			if (breakLoop) { break; }
		}
	}

	private boolean animation(Circle spaceObjectView, SpaceObject spaceObject) {
		// Animate node
		KeyValue xValue = new KeyValue(spaceObjectView.centerXProperty(), spaceObject.getPosition().getX());
		KeyValue yValue = new KeyValue(spaceObjectView.centerYProperty(), spaceObject.getPosition().getY());

		Timeline timeline = new Timeline();
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), xValue, yValue);
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();

		// Stop if node is outside screen
		double width = root.getWidth();
		double height = root.getHeight();
		if (spaceObjectView.getCenterX() <= width || spaceObjectView.getCenterY() <= height) {
			System.out.println("Node is beyond screen");
			return true;
		}

		return false;
	}
	

	// Draw nodes on screen

	private void showBlackCircle() {
		blackCircle = new Circle(948, 232, 54, Color.BLACK);
		root.getChildren().add(blackCircle);
	}

	private void showBlackHole() {
		eventHorizon.setRadius(blackHole.getRs());
		photonSphere.setRadiusX(blackHole.getPhotonSphere());
		photonSphere.setRadiusY(blackHole.getPhotonSphere());
		accretionDisk.setRadiusX(blackHole.getInnermostStableCircularOrbit());
		accretionDisk.setRadiusY(blackHole.getInnermostStableCircularOrbit());

		blackHole.setPosition(new Point2D(eventHorizon.getLayoutX(), eventHorizon.getLayoutY()));
	}

	private void showAsteroid() {
		spaceObjectView = new Circle(20, 650, 15, Color.web("#514531"));
		spaceObject = new Asteroid(new Point2D(spaceObjectView.getCenterX(), spaceObjectView.getCenterY()),
				new Point2D(2, 2), 100);

		root.getChildren().add(spaceObjectView);
	}

	private void showPlanet() {
		spaceObjectView = new Circle(20, 380, 20, Color.web("#836FFF"));
		spaceObject = new Planet(new Point2D(spaceObjectView.getCenterX(), spaceObjectView.getCenterY()),
				new Point2D(2, 2), 400);

		root.getChildren().add(spaceObjectView);
	}

	private void showStar() {
		spaceObjectView = new Circle(20, 350, 40, Color.web("#B8860B"));
		spaceObject = new Star(new Point2D(spaceObjectView.getCenterX(), spaceObjectView.getCenterY()),
				new Point2D(2, 2), 2000);

		root.getChildren().add(spaceObjectView);
	}

	private void showPhotons() {
		for (int i = 0; i < spaceObjectArray.length; i++) {
			Circle spaceObjectView = new Circle(50, ThreadLocalRandom.current().nextDouble(380, 750), 2.5,
					Color.web("#F0FFFF"));
			SpaceObject spaceObject = new Photon(
					new Point2D(spaceObjectView.getCenterX(), spaceObjectView.getCenterY()));

			spaceObjectViewArray[i] = spaceObjectView;
			spaceObjectArray[i] = spaceObject;
		}
		root.getChildren().addAll(spaceObjectViewArray);
	}
	
}
