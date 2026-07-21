module com.ramedis.datavisualization {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires org.kordamp.ikonli.javafx;


    opens com.ramedis.datavisualization to javafx.fxml;
    exports com.ramedis.datavisualization;
}