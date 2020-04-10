
package blackholesimulation;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpaceTime extends Application {

	/*
	 * private static final Point2D SPEED= new Point2D(5,5); private static final
	 * Point2D POSITION = new Point2D(80000,10); private static final double MASS=
	 * 10;
	 */

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/Views/Inicial_View.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();

		/*
		 * int cont=0;
		 * 
		 * BlackHole blackHole = new BlackHole(new Point2D(2,10),10000000);
		 * 
		 * Asteroid asteroid = new Asteroid(POSITION, SPEED, MASS); Planet planet = new
		 * Planet(POSITION, SPEED, MASS);
		 * 
		 * boolean sugado = false; while(sugado == false){ sugado =
		 * blackHole.pull(asteroid);
		 * 
		 * }
		 * 
		 */
	}

}