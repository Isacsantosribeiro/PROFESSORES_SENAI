module ProjectMercadinhoDoJoao {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.controlsfx.controls;
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens Controller to javafx.graphics, javafx.fxml;
	opens Model to javafx.graphics, javafx.fxml, javafx.base;
}
