package com.database_manager;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        database_manager db = new database_manager();
        UIHandler ui = new UIHandler(db);

        Scene scene = new Scene(ui.buildRoot(), 900, 600);
        stage.setTitle("MooseDB Manager"); // I have named the project
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}

class database_manager {

    private Database database;
    private UIHandler ui = new UIHandler(this); // pass in self to be updated

    // handle updates through ui handler
    public boolean add_new_table(String tableName) {
        if (database.table_names().contains(tableName)) { // check to make sure we're not overwriting a table
            return false;
        }
        database.new_table(tableName);
        return true;
    }

    public boolean remove_table(String tableName) {
        if (database.table_names().contains(tableName)) {
            database.delete_table(tableName);
            return true;
        }
        return false;
    }

    public Map get_values(String tableName) throws NoSuchElementException {
        if (database.table_names().contains(tableName)) {
            return database.get_values(tableName);
        }
        throw new NoSuchElementException();
    }

    public Set<String> get_tablenames(String tableName) {
        return database.table_names();
    }

    public void load_database(String filepath) {
        // loads database from json file
    }

    public void save_database(String filepath) {
        // saves database as a json file
    }

}


class Database {
    Map<String, Map<String, Data>> tables; // creates a set of tables.

    public void new_table(String tableName) {
        tables.put(tableName, new HashMap<>()); // should make a new empty table
    }
    public void delete_table(String tableName) {
        tables.remove(tableName);
    }
    public Map<String, Data> get_values(String tableName) { // gets the table from the tableName
        return tables.get(tableName);
    }
    public Set<String> table_names() {
        return tables.keySet();
    }
    public int table_length(String tableName) {
        return get_values(tableName).size(); // should return an int equal to the length of specified table
    }
    public Vector<Map<String, Integer>> main_table_vals() { // returns a list containing a map of table names and ints (rows of the table)
        Vector<Map<String, Integer>> a;
        Set<String> remaining = table_names(); // get remaining names of 
        for (String item : remaining) { // for each table name in set, add to the vector a

            // TODO: fix this please, trying to figure this out is killing me.
            //int temp = table_length(item);
            //a.add(Map<item, temp>);

        }

        return a; // returns uninitialized. don't care yet.
    }


    public void add_to_table(String tableName, Data data) {
        int index = table_length(tableName); // index is unique identifier, generate it
        tables.get(tableName).put(String.valueOf(index), data); // append data to index. index is length of table, so should start at index 0, and increment
    }

    public void remove_from_table(String tableName, int index) {
        tables.get(tableName).remove(String.valueOf(index));
    }

    public void remove_from_table(String tableName, String index) { // overload function just incase
        tables.get(tableName).remove(index);
    }

    public Data pop_back(String tableName) {
        String index = String.valueOf(table_length(tableName)-1);
        Data temp = tables.get(tableName).get(index);
        remove_from_table(tableName, index);
        return temp;
    }
}

// Data Variations and Classes

abstract class Data {
    // contains abstract functions that allow for inheritance
    protected // proteected, not private.
        int index; // index is not set yet

    
    public int get_index() {
        return this.index;
    }

    public void update_index(int index) {
        this.index = index; // may cause issues later on. 
    }

}

class Data_Integer extends Data {
    private
        int value;

    public Data_Integer(int value, int index) {
        this.value = value;
        this.index = index; // set index according to available indexes, function found when making a dataint in database manager.
    }
    
    public int get_value() {
        return this.value;
    }
    public void set_value(int value) {
        this.value = value;
    }
}

class Data_String extends Data {
    private
        String value;
    
    public Data_String(String value, int index) {
        this.value = value;
        this.index = index; // set index according to available indexes, function found when making a dataint in database manager.
    }
    
    public String get_value() {
        return this.value;
    }
    public void set_value(String value) {
        this.value = value;
    }
    
}

class Data_Double extends Data {
    private
        double value;
    
    public Data_Double(Double value, int index) {
        this.value = value;
        this.index = index; // set index according to available indexes, function found when making a dataint in database manager.
    }
    
    public Double get_value() {
        return this.value;
    }
    public void set_value(Double value) {
        this.value = value;
    }
    
}

class Data_Paragraph extends Data {
    private
        String[] value;
    
    public Data_Paragraph(String[] value, int index) {
        this.value = value;
        this.index = index; // set index according to available indexes, function found when making a dataint in database manager.
    }
    
    public String[] get_value() {
        return this.value;
    }
    public void set_value(String[] value) {
        this.value = value;
    }

    public String get_line(int index) {
        return this.value[index];
    }
    
}

class Data_Array extends Data {
    private
        Array value;
    
    public Data_Array(Array value, int index) {
        this.value = value;
        this.index = index;
    }

    public Array get_value() {
        return this.value;
    }
    public void set_value(Array value) {
        this.value = value;
    }

}


class RowData {

    private final SimpleStringProperty name;
    private final SimpleStringProperty type;
    private final SimpleStringProperty value;

    public RowData(String name, String type, String value) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.value = new SimpleStringProperty(value);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public String getName() {
        return name.get();
    }
}

class UIHandler {

    private TableView<RowData> tableView; // tableview is a list of the row data for now.

    private database_manager dbm; // allow access to the database manager for calling the functions inside

    public UIHandler(database_manager db) {
        this.dbm = db;
    }

    // for making the ui on first load
    public BorderPane buildRoot() {
        BorderPane root = new BorderPane();

        root.setTop(buildTopBar());
        root.setCenter(buildCenterTable());

        return root;
    }

    // the top bar that contains the buttons
    private ToolBar buildTopBar() {
        ToolBar toolBar = new ToolBar();

        Button newProjectBtn = new Button("New Project");
        Button saveProjectBtn = new Button("Save Project");
        Button loadProjectBtn = new Button("Load Project");
        Button newTableBtn = new Button("New Table");

        // get presses of buttons and map them to functions
        newProjectBtn.setOnAction(e -> onNewProject());
        saveProjectBtn.setOnAction(e -> onSaveProject());
        loadProjectBtn.setOnAction(e -> onLoadProject());
        newTableBtn.setOnAction(e -> onNewTable());

        
        // very nicely decorated toolbar with buttons
        toolBar.getItems().addAll(
                newProjectBtn,
                saveProjectBtn,
                loadProjectBtn,
                new Separator(),
                newTableBtn
        );

        return toolBar;
    }


    @SuppressWarnings("deprecation") // we shall in fact dwell on things of the past because it helps make my code work
    private TableView<RowData> buildCenterTable() {
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<RowData, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<RowData, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> data.getValue().typeProperty());

        TableColumn<RowData, String> valueCol = new TableColumn<>("Value");
        valueCol.setCellValueFactory(data -> data.getValue().valueProperty());

        tableView.getColumns().addAll(nameCol, typeCol, valueCol);


        setupRowDoubleClick();

        return tableView;
    }

    // check for double click
    private void setupRowDoubleClick() {
        tableView.setRowFactory(tv -> {
            TableRow<RowData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()
                        && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    onRowDoubleClick(row.getItem());
                }
            });
            return row;
        });
    }

    
    private ObservableList<RowData> createPlaceholderData() {
        return FXCollections.observableArrayList(
                new RowData("Users", "Table", "128 rows"),
                new RowData("Orders", "Table", "542 rows"),
                new RowData("Products", "Table", "87 rows"),
                new RowData("Logs", "Table", "12,340 rows")
        );
    }

    private ObservableList<RowData> buildFromArray() {
        // fix eventually, should get all values from array list and then build from them. function to get values does NOT work rn
        //TODO: FIX. function up top first.
    }



    private void onNewProject() {
        System.out.println("New Project clicked");
    }

    private void onSaveProject() {
        System.out.println("Save Project clicked");
    }

    private void onLoadProject() {
        System.out.println("Load Project clicked");
    }

    private void onNewTable() {
        System.out.println("New Table clicked");
    }

    private void onRowDoubleClick(RowData row) {
        System.out.println("Opened table: " + row.getName());
    }
}
