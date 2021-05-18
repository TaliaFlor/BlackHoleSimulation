package blackholesimulation.controllers;

import blackholesimulation.enums.SpaceBodies;
import blackholesimulation.space.BlackHole;
import blackholesimulation.space.SpaceObject;
import blackholesimulation.threads.PhotonsExecuter;
import blackholesimulation.utils.SpaceObjectFactory;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
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

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {

    private static final int PHOTONS_QTY = 100;
    private static final int PHOTONS_CHUNK = 50;    // Don't change this value

    private final BlackHole blackHole = new BlackHole(Math.pow(9.5, 6));

    private final SpaceObject[] spaceObjectArray = new SpaceObject[PHOTONS_QTY];
    private final Circle[] spaceObjectViewArray = new Circle[PHOTONS_QTY];

    private final SpaceBodies spaceBody;
    private SpaceObject spaceObject;
    private Circle spaceObjectView;

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
        showSpaceBody();
        showBlackHole();
        showBlackCircle();
    }

    private void showSpaceBody() {
        switch (spaceBody) {
            case ASTEROID:
                show(SpaceBodies.ASTEROID);
                break;
            case PLANET:
                show(SpaceBodies.PLANET);
                break;
            case STAR:
                show(SpaceBodies.STAR);
                break;
            case WHITE_DWARF:
                show(SpaceBodies.WHITE_DWARF);
                break;
            default:
                showPhotons();
                break;
        }
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

            Platform.runLater(new PhotonsExecuter(this, blackHole, photonsChunck, photonsViewChunck));
//            new Thread(new PhotonsExecuter(this, blackHole, photonsChunck, photonsViewChunck)).start();
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
     */
    private boolean isBeyondScreen(Circle spaceObjectView) {
        double width = root.getWidth();
        double height = root.getHeight();
        //			System.out.println("Node is beyond screen");
        return spaceObjectView.getCenterX() <= width || spaceObjectView.getCenterY() <= height;
    }


    // ===== Draw nodes on screen =====

    private void show(SpaceBodies type) {
        SpaceObjectFactory factory = new SpaceObjectFactory(type);
        spaceObjectView = factory.getSpaceObjectView();
        spaceObject = factory.getSpaceObject();

        root.getChildren().add(spaceObjectView);
    }

    private void showBlackCircle() {
        Circle blackCircle = new Circle(948, 232, 54, Color.BLACK);
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

    private void showPhotons() {
        for (int i = 0; i < spaceObjectArray.length; i++) {
            SpaceObjectFactory factory = new SpaceObjectFactory(SpaceBodies.PHOTON);
            Circle photonView = factory.getSpaceObjectView();
            SpaceObject photon = factory.getSpaceObject();

            spaceObjectViewArray[i] = photonView;
            spaceObjectArray[i] = photon;
        }
        root.getChildren().addAll(spaceObjectViewArray);
    }

}
