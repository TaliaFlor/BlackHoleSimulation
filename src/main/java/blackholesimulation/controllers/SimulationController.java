package blackholesimulation.controllers;

import blackholesimulation.space.BlackHole;
import blackholesimulation.spaceobjects.Asteroid;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {

	private BlackHole blackHole = new BlackHole(Math.pow(9.5, 6));

	private Asteroid asteroid;
	private Circle asteroidView;
	

	@FXML
	private AnchorPane root;
	@FXML
	private Ellipse accretionDisk;
	@FXML
	private Ellipse photonSphere;
	@FXML
	private Circle eventHorizon;
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showBlackHole();
		showAsteroid();

		pull();
	}
	
	

	@FXML
	private void back(ActionEvent event) throws IOException {

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../view/fxmls/Inicial_View.fxml"));

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		// Load fxml on scene
		stage.setTitle("Home Screen");
		stage.setScene(new Scene(fxmlloader.load()));
		stage.show();
	}
	

	private void pull() {
		boolean goInfinity = false;
		while (!goInfinity) {
			goInfinity = blackHole.pull(asteroid);
		}
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
		asteroidView = new Circle(50, 750, 20, Color.web("#514531"));
		asteroid = new Asteroid(new Point2D(asteroidView.getCenterX(), asteroidView.getCenterY()), new Point2D(5, 5),
				100);

		root.getChildren().add(asteroidView);
	}

}
