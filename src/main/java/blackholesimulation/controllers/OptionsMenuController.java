package blackholesimulation.controllers;

import java.io.IOException;

import blackholesimulation.enums.SpaceBodies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OptionsMenuController {

	private SimulationController simulationController;
	
	

	@FXML
	private void back(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../view/fxmls/Inicial_View.fxml"));

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		// Load fxml on scene
		stage.setTitle("Home Screen");
		stage.setScene(new Scene(fxmlloader.load()));
		stage.show();
	}
	

	// Choices
	
	@FXML
	private void asteroidPlay(ActionEvent event) throws IOException {
		simulationController = new SimulationController(SpaceBodies.ASTEROID);

		openSimulation(event);
	}

	@FXML
	private void photonPlay(ActionEvent event) throws IOException {
		simulationController = new SimulationController(SpaceBodies.PHOTON);

		openSimulation(event);
	}

	@FXML
	private void planetPlay(ActionEvent event) throws IOException {
		simulationController = new SimulationController(SpaceBodies.PLANET);

		openSimulation(event);
	}

	@FXML
	private void starPlay(ActionEvent event) throws IOException {
		simulationController = new SimulationController(SpaceBodies.STAR);

		openSimulation(event);
	}
	
	@FXML
	private void whiteDwarfPlay(ActionEvent event) throws IOException {
		simulationController = new SimulationController(SpaceBodies.WHITE_DWARF);

		openSimulation(event);
	}
	

	// Helper methods

	private void openSimulation(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../view/fxmls/Simulation_View.fxml"));
		fxmlloader.setController(simulationController);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		// Load fxml on scene
		stage.setTitle("Simulation");
		stage.setScene(new Scene(fxmlloader.load()));
		stage.show();
	}

}
