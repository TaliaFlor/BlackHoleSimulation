package blackholesimulation;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpaceTime extends Application {
	
	private static final Point2D SPEED= new Point2D(3,5);
    private static final Point2D POSITION = new Point2D(2,10);
    private static final double MASS= 1000;
    
    

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		  /*Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        */
        int cont=0;
        
        BlackHole blackHole = new BlackHole(new Point2D(10,10),100000);
        
        Asteroid asteroid = new Asteroid(POSITION, SPEED, MASS);
       
        while(cont<10){
        blackHole.pull(asteroid);
        cont++;
        }
       
       
    }
}
