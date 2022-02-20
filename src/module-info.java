module smartgreenhouse {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires de.jensd.fx.glyphs.fontawesome;
	requires jdk.jdi;
	requires java.sql;
	requires com.jfoenix;
	opens application to javafx.graphics, javafx.fxml;
	opens controlers to javafx.fxml;
	
}
