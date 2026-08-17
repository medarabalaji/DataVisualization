package com.ramedis.datavisualization;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.util.List;

public class ArrayListVisualizer implements Visualizer<String>{
    @Override
    public void visualize(List<String> data, HBox container) {
        container.getChildren().clear();

        for (int i=0; i<data.size(); i++){
            
            Label index = new Label(String.valueOf(i));

            Label value = new Label(data.get(i));

            VBox box = new VBox(index, value);

            container.getChildren().add(box);
        }
    }
}
