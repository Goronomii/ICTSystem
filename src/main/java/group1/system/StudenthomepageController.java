package group1.system;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
public class StudenthomepageController {
 private double MouseX, MouseY = 0;
@FXML
 private Stage stage;


@FXML 
private JFXButton todobutton;
@FXML 
    private JFXButton notificationbutton;
@FXML
    private Button minimizebutton;
    private String studentUsername;
    @FXML
    private ImageView logout;
@FXML
   private Label namelabel;

 @FXML 
 private JFXButton firstsemester;
 @FXML 
 private JFXButton secondsemester;
 @FXML
private Text dateTimeText;
 @FXML
    private void close() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Press OK to exit, or Cancel to stay.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
    @FXML
    private void minimize_clicked(MouseEvent event) {
        Stage stage = (Stage) minimizebutton.getScene().getWindow();
        stage.setIconified(true);
    }
    

    @FXML
     public void initData(String studentUsername) throws SQLException {
       this.studentUsername = studentUsername;
        namelabel.setText(studentUsername);
         
        
    }

      @FXML
    private void initialize() {
        logout.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), logout);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        logout.setOnMouseExited(event -> {  
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), logout);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
         logout.setOnMousePressed(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), logout);
        scaleTransition.setToX(0.9);
        scaleTransition.setToY(0.9);
        scaleTransition.play();
    });

    logout.setOnMouseReleased(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), logout);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    });
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(1), event -> {
            dateTimeText.setText(LocalDateTime.now().format(formatter));
        })
    );
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    }
     
     
     
  
 
@FXML
private void logout(MouseEvent event) throws IOException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("Are you sure you want to logout?");
    alert.setContentText("Press OK to logout, or Cancel to stay.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);

        // Make the stage movable
        movable(primaryStage, scene);

        primaryStage.show();
    }
}


private void movable(Stage stage, Scene scene) { 
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
private void showSemesterWindow(ActionEvent event) throws IOException, SQLException {
    JFXButton button = (JFXButton) event.getSource();
    button.setDisable(true);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/secondsemesterICTprogrammingsubject.fxml"));
    Parent root = loader.load();
    secondsemesterICTSubjectController semesterController = loader.getController();
    Stage semesterStage = new Stage();
    semesterController.setSemesterStage(semesterStage);
    semesterController.initData(studentUsername);
    
    semesterStage.setScene(new Scene(root));
    semesterStage.initStyle(StageStyle.TRANSPARENT);
    


    semesterStage.show();
    movable(semesterStage, root.getScene());
    semesterStage.setOnHidden(e -> button.setDisable(false));
}
@FXML
private void showfirstSemesterWindow(ActionEvent event) throws IOException, SQLException {
    JFXButton button = (JFXButton) event.getSource();
    button.setDisable(true);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/group1/system/firstsemesterICTprogrammingsubject.fxml"));
    Parent root = loader.load();
    firstsemesterICTSubjectController semesterController = loader.getController();
    Stage semesterStage = new Stage();
    semesterController.setSemesterStage(semesterStage);
    semesterController.initData(studentUsername);
    
    semesterStage.setScene(new Scene(root));
    semesterStage.initStyle(StageStyle.TRANSPARENT);
    

    semesterStage.show();
    movable(semesterStage, root.getScene());
    semesterStage.setOnHidden(e -> button.setDisable(false));
}
     
     
     
     
     
     
     
     
     
     //getter
   private int getUserId(String studentUsername) throws SQLException {
        int userId = -1;
        String url = "jdbc:mysql://localhost:3306/usersinfo";
        String dbuser = "root";
        String dbpass = "Rootpassword123";

        try (Connection connection = DriverManager.getConnection(url, dbuser, dbpass)) {
            String sql = "SELECT UID FROM userprivateinfo WHERE Username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, studentUsername);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        userId = resultSet.getInt("UID");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}