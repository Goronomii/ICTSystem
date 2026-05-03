/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;
import javafx.animation.FadeTransition;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Delmark Lusterio
 */
public class aboutSystemController {
          private double MouseX, MouseY = 0;
    @FXML
    private Button minimizebutton;
    @FXML
    private JFXButton helpfeedbackbutton;
     @FXML
    private JFXButton contactbutton;
     @FXML
     private ImageView group;
    @FXML
      private HostServices hostServices;
    
    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
    @FXML
    private void close() throws IOException {
        Platform.exit();
    }
   @FXML
    private void imageViewPressed(MouseEvent event) {
        // Increase the size of the ImageView
        group.setScaleX(1.1);
        group.setScaleY(1.1);
    }

    // Method to handle mouse release event on ImageView
    @FXML
    private void imageViewReleased(MouseEvent event) {
        // Reset the size of the ImageView
        group.setScaleX(1.0);
        group.setScaleY(1.0);
    }

    @FXML
    private void minimize_clicked(MouseEvent event) {
        Stage stage = (Stage) minimizebutton.getScene().getWindow();
        stage.setIconified(true);
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
    @FXML
public void loadlogin(ActionEvent event) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/primary.fxml"));
    Parent root = loader.load();
    PrimaryController PrimaryController = loader.getController(); // Controller naming convention usually starts with a lowercase letter
    
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Ensure the stage is visible before starting the transition   
    stage.setScene(scene);
    stage.show();
    
    // Set initial opacity to 0 for fade-in effect
    root.setOpacity(0);
    
    // Fade animation
    FadeTransition transition = new FadeTransition(Duration.seconds(2), root);
    transition.setToValue(1); // Fade to fully visible
    transition.play();
    
    // Make the stage movable
    movable(scene, stage);
}
    @FXML
    private void sendFeedback(ActionEvent event) {
        String recipientEmail = "jamesdionisio288@gmail.com";
        String subject = "Feedback";
        String body = "Please enter your feedback here.";

        try {
            recipientEmail = URLEncoder.encode(recipientEmail, StandardCharsets.UTF_8.toString());
            subject = URLEncoder.encode(subject, StandardCharsets.UTF_8.toString());
            body = URLEncoder.encode(body, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }

        String mailtoUrl = String.format("mailto:%s?subject=%s&body=%s", recipientEmail, subject, body);

        if (hostServices != null) {
            hostServices.showDocument(mailtoUrl);
        } else {
            System.err.println("HostServices is not initialized");
        }
    }
}
