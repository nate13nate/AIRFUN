package menu;

import database.Database;
import database.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import mainfiles.Main;

import java.util.ArrayList;

public class HighScorePage {
    public static Scene makeScoresPage(Button button) {
        VBox highScores = new VBox();

        // high scores page
        ArrayList<User> users = new ArrayList<>();
        users = Database.getHighScores();

        TableView<User> table = new TableView<>();
        ScoresTable scoresTable = new ScoresTable();
        table = scoresTable.makeTable(users);

        highScores.getChildren().addAll(table, button);
        return new Scene(highScores, Main.GAME_WIDTH, Main.GAME_HEIGHT);
    }
}
