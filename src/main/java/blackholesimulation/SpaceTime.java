package blackholesimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SpaceTime extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("view/fxmls/Inicial_View.fxml"));

        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("blackholesimulation/view/images/icon.png"));
		stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
