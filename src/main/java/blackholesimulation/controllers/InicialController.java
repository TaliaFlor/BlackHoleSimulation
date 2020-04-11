package blackholesimulation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class InicialController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button Info;
    @FXML
    private Button play;

    @FXML 
    private void showInfoView(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../view/fxmls/Info_View.fxml"));
        // Define fmxl's controller
        //fxmlloader.setController(new BoardController());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Load fxml on scene
        stage.setTitle("Info");
        stage.setScene(new Scene(fxmlloader.load()));
        stage.show();
    }
    
    
     @FXML
    void showSimulationView(ActionEvent event) {
        
        

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }

}

    
    
   
    

