module ProjectMercadinhoDoJoao {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Controller to javafx.graphics, javafx.fxml;
}
