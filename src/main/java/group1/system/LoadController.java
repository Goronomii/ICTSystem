/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Delmark Lusterio
 */
public class LoadController {
       private static Scene scene;
        private Stage stage;
       private double MouseX, MouseY = 0;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Text textLoading;
    @FXML 
    private Text percentloading;
    @FXML
    private ImageView LogoSHS;
    @FXML
    private Text TextTitle;
    
    @FXML
    private AnchorPane mainAnchorPane;
       public void setStage(Stage stage) {
        this.stage = stage;
    }


public void updateProgress(double progress) {
    Platform.runLater(() -> {
        progressBar.setProgress(progress);
        // Calculate percentage
        int percentage = (int) (progress * 100);
        // Update text with percentage
        percentloading.setText(percentage + "%");
    });
}
    public void updateText(String message) {
        Platform.runLater(() -> textLoading.setText(message));
    }
public void updateLoadingStatus(double progress) {
    String[] messages = {
        "Loading...",
        "Initializing....",
        "Student and Teacher Portal System....",
        "Finalizing...",
        "Welcome!"
    };

    int index = (int) (progress * (messages.length - 1));
    String message = messages[index];

    updateText(message); // Update message


    if (index == messages.length - 1) {
        progress = 1.0;
    }

    updateProgress(progress); // Update progress

    // If progress is complete, initiate the transition to the primary scene after a delay
    if (progress >= 1.0) {
        // Add a delay before transitioning to ensure "Welcome!" message is displayed
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> transitionToPrimaryScene());
        delay.play();
    }
}
   @FXML
    private void movable(Scene scene, Stage stage) {
         scene.setOnMousePressed(event ->{
                MouseX = event.getSceneX();
                MouseY = event.getSceneY();
            });
            scene.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - MouseX);
                stage.setY(event.getScreenY() - MouseY); 
            });
    }
   
   public void transitionToPrimaryScene() {
   if (stage == null) {
            System.err.println("Stage is null, cannot transition to primary scene.");
            return;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            movable(scene, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    @FXML
    private void initialize() {
        progressBar.getStyleClass().add("custom-progress-bar");
        mainAnchorPane.getStyleClass().add("custom-main-pane");
        

    // Add slide animation to TextTitle
    FadeTransition textFadeTransition = new FadeTransition(Duration.seconds(4), TextTitle);
    textFadeTransition.setFromValue(0.0);
    textFadeTransition.setToValue(1.0);
    textFadeTransition.play();
    }
}
