package com.Exercise15_7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage mainsStage) {
        Circle c = new Circle(150, 150, 30);
        c.setFill(Color.BLACK);

        Pane pane = new Pane();

        
        c.setOnMousePressed(e -> { // can't be white or cant be seen
            c.setFill(Color.GREY);
        });
        c.setOnMouseReleased(e -> {
            c.setFill(Color.BLACK);
        });
        pane.getChildren().add(c); // add circle to pane

        Scene scene = new Scene(pane, 300, 300);

        // show the pane
        mainsStage.setTitle("circle on click");
        mainsStage.setScene(scene);
        mainsStage.show();
    }

    public static void main(String[] args) { 
        launch(args); 
    }
}
