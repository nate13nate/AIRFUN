package menu;

import database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.awt.*;
import java.util.ArrayList;

public class ScoresTable {
    public static TableView makeTable(ArrayList<User> users) {
        //Label for education
        Label label = new Label("File Data:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);

        TableView<User> table = new TableView<>();
        ObservableList<User> data = FXCollections.observableArrayList(users);

        // Creating score and user columns
        TableColumn<User, Integer> scoreCol = new TableColumn("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("score"));
        TableColumn<User, String> nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));

        ObservableList<User> list = FXCollections.observableArrayList();
        table.setItems(data);
        table.getColumns().addAll(nameCol, scoreCol);

        return table;
    }
}
