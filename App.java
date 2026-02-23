package com.Exercise35_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;




public class App extends Application {

    String connectionPoint = "None";
    Boolean connectionValid = false;

    @Override
    public void start(Stage stage) {
        DatabaseManager.initialize();
        
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(2, 20, 2, 20));
        Scene ui = new Scene(pane, 500, 500);

        TextArea txa = new TextArea();

        Button run = new Button("Run Operations");
        Button connectButton = new Button("Connect To Database...");

        pane.add(txa, 0, 0);
        pane.add(run, 0, 1);
        pane.add(connectButton, 0, 2);

        connectButton.setOnAction(eh-> {
            openNewWindow();
        });

        
        
        stage.setScene(ui);

        stage.setTitle("Database Batch Updating");
        stage.show();

        run.setOnAction(eh-> {
            
            txa.setText(DatabaseManager.nonBatchInsert(connectionPoint));
            txa.setText(txa.getText() + "\n\n" + DatabaseManager.batchInsert(connectionPoint));

        });
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void openNewWindow() {
        Stage newWindow = new Stage();
        newWindow.setTitle("Database Connection");

        Label dbc = new Label("Connected to: " + connectionPoint);

        GridPane jd = new GridPane();
        Label jdbcLabel = new Label("JDBC Drive: ");
        TextField jdbc = new TextField();

        jdbc.setOnAction(eh -> {
            connectionPoint = "jdbc:sqlite:" + jdbc.getText();
            if (checkDB(jdbc.getText())) {
                dbc.setText("Connected to: " + connectionPoint);
            } else {
                dbc.setText("Connection Not Established to " + connectionPoint);
            }
        });

        jd.add(jdbcLabel, 0, 0);
        jd.add(jdbc, 1, 0);

        GridPane url = new GridPane();
        Label urlLabel = new Label("Database URL: ");
        TextField urlTF = new TextField();

        url.add(urlLabel, 0, 0);
        url.add(urlTF, 1, 0);

        GridPane usr = new GridPane();
        Label usrLabel = new Label("Username: ");
        TextField usrTF = new TextField();

        usr.add(usrLabel, 0, 0);
        usr.add(usrTF, 1, 0);

        GridPane psw = new GridPane();
        Label pswLabel = new Label("Password: ");
        TextField pswTF = new TextField();

        psw.add(pswLabel, 0, 0);
        psw.add(pswTF, 1, 0);

        

        
        Button cnnct = new Button("Connect");

        cnnct.setOnAction(eh-> {
            
            
            connectionPoint = "jdbc:sqlite:" + jdbc.getText();
            if (checkDB(jdbc.getText())) {
                dbc.setText("Connected to: " + connectionPoint);
                newWindow.close();
            } else {
                dbc.setText("Connection Not Established to " + connectionPoint);
            }

        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> newWindow.close());

        VBox layout = new VBox(10, dbc, jd, url, usr, psw, cnnct, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 300, 300);
        newWindow.setScene(scene);


        newWindow.showAndWait(); // Use showAndWait() for a blocking dialog
    }

    public boolean checkDB(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.getName().endsWith(".db")) {
                return true;
            }
            
        }
        return false;
    }


}

class DatabaseManager {

    private static final String URL = "jdbc:sqlite:update.db";

    public static void initialize() {
        String drop = "DROP TABLE IF EXISTS Temp";
        String create = "CREATE TABLE Temp (num1 DOUBLE, num2 DOUBLE, num3 DOUBLE)";

        try (Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement()) {

            stmt.execute(drop);
            stmt.execute(create);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String batchInsert(String url) {
        long start = System.nanoTime();

        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt =
                    conn.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)")) {

            conn.setAutoCommit(false);

            for (int i = 0; i < 10_000; i++) {
                stmt.setDouble(1, Math.random());
                stmt.setDouble(2, Math.random());
                stmt.setDouble(3, Math.random());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();
        return "Batch time (ms): " + (end - start) / 1_000_000.0;
    }

    public static String nonBatchInsert(String url) {
        long start = System.nanoTime();

        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt =
                    conn.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)")) {

            conn.setAutoCommit(false);

            for (int i = 0; i < 10_000; i++) {
                stmt.setDouble(1, Math.random());
                stmt.setDouble(2, Math.random());
                stmt.setDouble(3, Math.random());
                stmt.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();
        return "Non-batch time (ms): " + (end - start) / 1_000_000.0;
    }




}