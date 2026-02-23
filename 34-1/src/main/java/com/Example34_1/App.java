package com.Exercise34_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.geometry.*;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        DatabaseManager.initialize();
        VBox pane = new VBox();

        Entry id = new Entry("ID");
        pane.getChildren().add(id.get_gp());

        Entry ln = new Entry("Last Name");
        pane.getChildren().add(ln.get_gp());

        Entry fn = new Entry("First Name");
        pane.getChildren().add(fn.get_gp());
        
        Entry mi = new Entry("MI");
        pane.getChildren().add(mi.get_gp());
        
        Entry ad = new Entry("Address");
        pane.getChildren().add(ad.get_gp());
        
        Entry ci = new Entry("City");
        pane.getChildren().add(ci.get_gp());
        
        Entry st = new Entry("State");
        pane.getChildren().add(st.get_gp());
        
        Entry tf = new Entry("Telephone");
        pane.getChildren().add(tf.get_gp());

        Entry em = new Entry("Email");
        pane.getChildren().add(em.get_gp());

        // buttons
        Button view = new Button("View");
        Button insert = new Button("Insert");
        Button update = new Button("Update");
        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(2, 10, 2, 20));
        buttons.add(view, 0, 0);
        buttons.add(new Separator(Orientation.VERTICAL), 1, 0);
        buttons.add(insert, 2, 0);
        buttons.add(new Separator(Orientation.VERTICAL), 3, 0);
        buttons.add(update, 4, 0);
        pane.getChildren().add(buttons);

        view.setOnAction(eh-> {
            StaffMember a = getStaffMember(id.get_tf()); // get the id and parse it to get staff member off id
            ln.st(a.last);
            fn.st(a.first);
            mi.st(a.mi);
            ad.st(a.add);
            ci.st(a.city);
            st.st(a.state);
            tf.st(a.telephone);
            em.st(a.email);
            
        });
        insert.setOnAction(eh-> {
            insertStaff(
                id.get_tf(),
                ln.get_tf(), 
                fn.get_tf(), 
                mi.get_tf(), 
                ad.get_tf(), 
                ci.get_tf(), 
                st.get_tf(), 
                tf.get_tf(),
                em.get_tf());
        });

        update.setOnAction(eh-> {
            updateRecord(
                id.get_tf(),
                ln.get_tf(), 
                fn.get_tf(), 
                mi.get_tf(), 
                ad.get_tf(), 
                ci.get_tf(), 
                st.get_tf(), 
                tf.get_tf(),
                em.get_tf());
        });


        Scene ui = new Scene(pane, 500, 500);
        stage.setScene(ui);

        stage.setTitle("Staff Database");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static StaffMember getStaffMember(String id) {

        String sql = "SELECT * FROM Staff WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:staff.db");
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    StaffMember a = new StaffMember();

                    a.last = rs.getString("lastName");
                    a.first = rs.getString("firstName");
                    a.mi = rs.getString("mi");
                    a.add = rs.getString("address");
                    a.city = rs.getString("city");
                    a.state = rs.getString("state");
                    a.telephone = rs.getString("telephone");
                    a.email = rs.getString("email");

                    return a;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
}

    public static void insertStaff(String id, String lastName, String firstName, String mi, String addr, String city, String state, String telephone, String email) {

    String sql = "INSERT OR IGNORE INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:staff.db");
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, id);
        stmt.setString(2, lastName);
        stmt.setString(3, firstName);
        stmt.setString(4, mi);
        stmt.setString(5, addr);
        stmt.setString(6, city);
        stmt.setString(7, state);
        stmt.setString(8, telephone);
        stmt.setString(9, email);



        stmt.executeUpdate();
        System.out.println("Staff inserted.");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public static void updateRecord(String id,
                                String lastName,
                                String firstName,
                                String mi,
                                String addr,
                                String city,
                                String state,
                                String telephone,
                                String email) {

    String sql = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?";

    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:staff.db");
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, lastName);
        stmt.setString(2, firstName);
        stmt.setString(3, mi);
        stmt.setString(4, addr);
        stmt.setString(5, city);
        stmt.setString(6, state);
        stmt.setString(7, telephone);
        stmt.setString(8, email);
        stmt.setString(9, id);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected == 0) {
            System.out.println("No record found with id: " + id);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

class StaffMember {
    public String id = "";
    public String first = "";
    public String last = "";
    public String mi = "";
    public String add = "";
    public String city = "";
    public String state = "";
    public String telephone = "";
    public String email = "";
}

class Entry {

    private TextField tf = new TextField("");
    private GridPane grid = new GridPane();

    public Entry(String name) {
        Text tx = new Text(name + ": ");
        grid.setPadding(new Insets(2, 10, 2, 20));
        grid.add(tx, 0, 0);
        grid.add(tf, 1, 0);
    }

    public GridPane get_gp() {
        return grid;
    }

    public String get_tf() {
        return tf.getText();
    }

    public void st(String a) {
        tf.setText(a);
    }
}

class DatabaseManager {

    private static final String URL = "jdbc:sqlite:staff.db";

    public static void initialize() {
        String sql = "CREATE TABLE IF NOT EXISTS Staff (id CHAR(9) NOT NULL, lastName VARCHAR(15), firstName VARCHAR(15), mi CHAR(1), address VARCHAR(20), city VARCHAR(20), state CHAR(2), telephone CHAR(10), email VARCHAR(40), PRIMARY KEY (id)); ";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

