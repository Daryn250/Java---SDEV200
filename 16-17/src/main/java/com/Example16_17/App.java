package com.Exercise16_17;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private Text t;
    private Slider a;
    private Slider b;
    private Slider c;
    private Slider d;

    @Override
    public void start(Stage mainsStage) {
        
        GridPane sliderpanel = new GridPane();
        sliderpanel.setHgap(15);
        sliderpanel.setVgap(20);
        sliderpanel.setPadding(new Insets(20));
        sliderpanel.setAlignment(Pos.CENTER);
        t = new Text(10, 10, "Show Colors");

        a = new Slider(0, 255, 128);
        a.setBlockIncrement(1);
        b = new Slider(0, 255, 128);
        b.setBlockIncrement(1);
        c = new Slider(0, 255, 128);
        c.setBlockIncrement(1);
        d = new Slider(0, 100, 50);
        d.setBlockIncrement(1);

        

        a.valueProperty().addListener((obs, oldVal, newVal) -> updateColor());
        b.valueProperty().addListener((obs, oldVal, newVal) -> updateColor());
        c.valueProperty().addListener((obs, oldVal, newVal) -> updateColor());
        d.valueProperty().addListener((obs, oldVal, newVal) -> updateColor());


        // add to pane
        sliderpanel.add(t, 0, 0);
        sliderpanel.add(a, 0, 1);
        sliderpanel.add(b, 0, 2);
        sliderpanel.add(c, 0, 3);
        sliderpanel.add(d, 0, 4);
        // make scene (joke in here somewhere)
        Scene scene = new Scene(sliderpanel, 300, 300);

        // show the pane
        mainsStage.setTitle("circle on click");
        mainsStage.setScene(scene);
        mainsStage.show();
    }
    private void updateColor() {
        double red = a.getValue()/255.0;
        double green = b.getValue()/255.0;
        double blue = c.getValue()/255.0;
        double opacity = d.getValue()/100.0;

        Color color = new Color(red, green, blue, opacity);
        t.setFill(color);
    }

    public static void main(String[] args) { 
        launch(args); 
    }
}
