module group1.system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens group1.system to javafx.fxml;
    exports group1.system;
    requires com.jfoenix;
    requires javafx.web;

    
}
