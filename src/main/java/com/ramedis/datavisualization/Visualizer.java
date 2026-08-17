package com.ramedis.datavisualization;

import javafx.scene.layout.HBox;

import java.util.List;

public interface Visualizer <T>{
    void visualize(
            List<T> data, HBox container
    );
}
