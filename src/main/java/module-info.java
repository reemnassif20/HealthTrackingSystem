module com.example.healthtrackingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.healthtrackingsystem to javafx.fxml;
    opens Controllers to javafx.fxml;
    exports com.example.healthtrackingsystem;
}
