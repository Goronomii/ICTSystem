package group1.system;
import java.io.IOException;
import java.sql.SQLException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController {
    private Stage stage;
    private String username;
    private String role;
    private AnchorPane parentContainer;
     private double MouseX, MouseY = 0;;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsername(String username) {
        this.username = username;
    }
      public void setRole(String role) {
        this.role = role;
    }
       public String getUsername() {
        return username;
    }
       public String getRole() {
        return role;
    }
       
       @FXML
    private void movable(Scene scene) {
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
public void loadStudentHomepage(String studentUsername) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/student_home.fxml"));
    Parent root = loader.load();
    StudenthomepageController controllerstudenthome = loader.getController();
    controllerstudenthome.initData(studentUsername);
    Scene scene = new Scene(new AnchorPane(root));

    FadeTransition transition = new FadeTransition(Duration.seconds(1.5), root);
    transition.setFromValue(0); 
    transition.setToValue(1); 
    transition.play();

    movable(scene);
    stage.setScene(scene);
}

@FXML
public void loadTeacherHomepage(String teacherUsername) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/teacher_home.fxml"));
    Parent root = loader.load();
    TeacherhomepageController controllerteacherhome = loader.getController();
    controllerteacherhome.initData(teacherUsername);
    Scene scene = new Scene(new AnchorPane(root));

    FadeTransition transition = new FadeTransition(Duration.seconds(1.5), root);
    transition.setFromValue(0); 
    transition.setToValue(1); 
    transition.play();

    movable(scene);    
    stage.setScene(scene);
}
}
