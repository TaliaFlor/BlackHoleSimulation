package blackholesimulation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import blackholesimulation.enums.SpaceBodies;
import blackholesimulation.threads.PhotonsExecuter;
import blackholesimulation.space.BlackHole;
import blackholesimulation.space.SpaceObject;
import blackholesimulation.spaceobjects.Asteroid;
import blackholesimulation.spaceobjects.Photon;
import blackholesimulation.spaceobjects.Planet;
import blackholesimulation.spaceobjects.Star;
import blackholesimulation.spaceobjects.WhiteDwarf;
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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimulationController implements Initializable {

	private static final int PHOTONS_QTY = 100;
	private static final int PHOTONS_CHUNK = 50;	// Don't change this value

	private final BlackHole blackHole = new BlackHole(Math.pow(9.5, 6));

	private final SpaceObject[] spaceObjectArray = new SpaceObject[PHOTONS_QTY];
	private final Circle[] spaceObjectViewArray = new Circle[PHOTONS_QTY];

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
		draw();
		pull();
	}
	
	

	/**
	 * <p>
	 * Draw nodes on screen
	 * </p>
	 */
	private void draw() {
		if (spaceBody == SpaceBodies.ASTEROID) {
			showAsteroid();
		} else if (spaceBody == SpaceBodies.PLANET) {
			showPlanet();
		} else if (spaceBody == SpaceBodies.STAR) {
			showStar();
		} else if (spaceBody == SpaceBodies.WHITE_DWARF) {
			showWhiteDwarf();
		} else {
			showPhotons();
		}
		showBlackHole();
		showBlackCircle();
	}
	
	/**
	 * <p>Pull space objects towards the black hole.</p>
	 */
	private void pull() {
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
	

	
	// ===== Animation =====
	
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
		for (int i = 0; i < spaceObjectArray.length; i += PHOTONS_CHUNK) {
			SpaceObject[] photonsChunck = Arrays.copyOfRange(spaceObjectArray, i,
					Math.min(spaceObjectArray.length, i + PHOTONS_CHUNK));
			Circle[] photonsViewChunck = Arrays.copyOfRange(spaceObjectViewArray, i,
					Math.min(spaceObjectViewArray.length, i + PHOTONS_CHUNK));

			new Thread(new PhotonsExecuter(this, blackHole, photonsChunck, photonsViewChunck)).start();
		}
	}

	public boolean animation(Circle spaceObjectView, SpaceObject spaceObject) {
		// Animate node
		KeyValue xValue = new KeyValue(spaceObjectView.centerXProperty(), spaceObject.getPosition().getX());
		KeyValue yValue = new KeyValue(spaceObjectView.centerYProperty(), spaceObject.getPosition().getY());

		Timeline timeline = new Timeline();
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), xValue, yValue);
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();

		return isBeyondScreen(spaceObjectView);
	}

	/**
	 * <p>Stop if node is outside screen</p>
	 * @param spaceObjectView
	 * @return
	 */
	private boolean isBeyondScreen(Circle spaceObjectView) {
		double width = root.getWidth();
		double height = root.getHeight();
		if (spaceObjectView.getCenterX() <= width || spaceObjectView.getCenterY() <= height) {
//			System.out.println("Node is beyond screen");
			return true;
		}
		return false;
	}
	
	

	// ===== Draw nodes on screen =====

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
	
	private void showWhiteDwarf() {
		RadialGradient fill = new RadialGradient(0, 0, 0.5037036895751953, 0.4873015630812872, 0.5, true,
				CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(0.5110795117904966, Color.web("#fffcfc")),
				new Stop(1, Color.web("#cccccc")));

		spaceObjectView = new Circle(20, 600, 25, fill);
		spaceObjectView.setStroke(Color.web("#d4d4d4"));
		
		spaceObject = new WhiteDwarf(new Point2D(spaceObjectView.getCenterX(), spaceObjectView.getCenterY()),
				new Point2D(2, 2), 2000);

		root.getChildren().add(spaceObjectView);
	}

	private void showPhotons() {
		for (int i = 0; i < spaceObjectArray.length; i++) {
			Circle photonView = new Circle(50, ThreadLocalRandom.current().nextDouble(380, 750), 2.5,
					Color.web("#F0FFFF"));
			SpaceObject photon = new Photon(
					new Point2D(photonView.getCenterX(), photonView.getCenterY()));

			spaceObjectViewArray[i] = photonView;
			spaceObjectArray[i] = photon;
		}
		root.getChildren().addAll(spaceObjectViewArray);
	}
	
}
