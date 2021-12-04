package menu;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import database.User;

public class SettingData extends Application {
    public void start(Stage stage) {
        //Label for education
        Label label = new Label("File Data:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);

        //Creating a table view
        TableView<User> table = new TableView<User>();
        final ObservableList<User> data = FXCollections.observableArrayList(
                new User(100, "Jim", "pass"),
                new User(200, "Tim", "pass"),
                new User(300, "Kim", "pass"),
                new User(400, "Nim", "pass")
        );

        //Creating columns
        TableColumn<User, Integer> scoreCol = new TableColumn("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("score"));
        TableColumn<User, String> nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));

        //Adding data to the table
        ObservableList<String> list = FXCollections.observableArrayList();
        table.setItems(data);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(scoreCol, nameCol);

        //Setting the size of the table
        table.setMaxSize(350, 200);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 50, 50, 60));
        vbox.getChildren().addAll(label, table);

        //Setting the scene
        Scene scene = new Scene(vbox, 595, 230);
        stage.setTitle("Table View Example");
        stage.setScene(scene);
        stage.show();

        for (int i = 0; i < table.getItems().size(); i++) {
            System.out.println(table.getItems().get(i));
        }
    }
    public static void main(String args[]){
        launch(args);
    }
}