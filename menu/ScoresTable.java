package menu;

import database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class ScoresTable {
    public static TableView makeTable(ArrayList<User> users) {
        ArrayList<User> topUsers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topUsers.add(users.get(i));
        }

        TableView<User> table = new TableView<>();
        ObservableList<User> data = FXCollections.observableArrayList(topUsers);

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
