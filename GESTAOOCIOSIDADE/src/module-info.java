module AGENDAMENTOS {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens application to javafx.graphics, javafx.fxml;
    opens Controller  to javafx.graphics, javafx.fxml;
    opens Model to javafx.base, javafx.controls, javafx.fxml, javafx.graphics; 
}