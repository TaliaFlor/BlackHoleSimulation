package blackholesimulation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void backInicialView(ActionEvent event) throws IOException {

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../view/fxmls/Inicial_View.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Load fxml on scene
        stage.setTitle("Info");
        stage.setScene(new Scene(fxmlloader.load()));
        stage.show();
    }

}
