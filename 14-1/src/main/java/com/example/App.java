package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage mainsStage) {

        //setup gridpane and width and height
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(15));
        double width = 150;
        double height = 100;

        // make images and apply them to imageviews
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/flag1.gif")));
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/flag2.gif")));
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/flag6.gif")));
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/flag7.gif")));

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(image4);

        // set image view settings
        imageView1.setFitWidth(width);
        imageView1.setFitHeight(height);
        imageView1.setPreserveRatio(true);

        imageView2.setFitWidth(width);
        imageView2.setFitHeight(height);
        imageView2.setPreserveRatio(true);

        imageView3.setFitWidth(width);
        imageView3.setFitHeight(height);
        imageView3.setPreserveRatio(true);

        imageView4.setFitWidth(width);
        imageView4.setFitHeight(height);
        imageView4.setPreserveRatio(true);


        // add the views to the pane
        pane.add(imageView1, 0, 0); // top and left
        pane.add(imageView2, 1, 0); // top and right
        pane.add(imageView3, 0, 1); // bottom and left
        pane.add(imageView4, 1, 1); // bottom and right

        // show the pane
        Scene scene = new Scene(pane);
        mainsStage.setTitle("4 images");
        mainsStage.setScene(scene);
        mainsStage.show();
    }

    public static void main(String[] args) { 
        launch(args); 
    }
}
