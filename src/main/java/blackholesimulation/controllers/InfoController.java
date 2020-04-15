package blackholesimulation.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InfoController {

	@FXML
	private void backInicialView(ActionEvent event) throws IOException {

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../view/fxmls/Inicial_View.fxml"));

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		// Load fxml on scene
		stage.setTitle("Home Screen");
		stage.setScene(new Scene(fxmlloader.load()));
		stage.show();
	}

}
