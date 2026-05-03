package group1.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.HostServices;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class App extends Application {

    private static Scene scene;
    private double MouseX, MouseY = 0;
     private static HostServices hostServicesInstance;
 @Override
    public void start(Stage stage) throws IOException {
           hostServicesInstance = getHostServices();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("load.fxml"));
            Parent root = loader.load();
            LoadController loadController = loader.getController();
            loadController.setStage(stage); // Pass the stage to the controller
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);

            scene.setOnMousePressed(event -> {
                MouseX = event.getSceneX();
                MouseY = event.getSceneY();
            });
            scene.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - MouseX);
                stage.setY(event.getScreenY() - MouseY);
            });

            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            // Simulate progress and update loading status using Timeline
            Timeline timeline = new Timeline();
            for (int i = 0; i <= 50; i++) {
                double progress = i * 0.02;
                timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(i * 0.1), e -> loadController.updateLoadingStatus(progress))
                );
            }
            timeline.setOnFinished(event -> loadController.updateLoadingStatus(1.0));
            timeline.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static HostServices getHostServicesInstance() {
        return hostServicesInstance;
    }
    public static void main(String[] args) {
        launch();
    }

}