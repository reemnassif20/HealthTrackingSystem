module com.example.healthtrackingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.example.healthtrackingsystem to javafx.fxml;
    opens com.example.healthtrackingsystem.Controllers to javafx.fxml, javafx.base;
    exports com.example.healthtrackingsystem;
}
