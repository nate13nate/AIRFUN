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
    public static Scene makeScoresPage(Button button, ArrayList<User> users) {
        VBox highScores = new VBox();
        highScores.setStyle("-fx-background-image: url('./images/background.png'); -fx-background-size: cover; -fx-font-size: 18pt; -fx-font-family: SansSerif Bold;");

        TableView<User> table = new TableView<>();
        ScoresTable scoresTable = new ScoresTable();
        table = scoresTable.makeTable(users);

        highScores.getChildren().addAll(table, button);
        return new Scene(highScores, Main.GAME_WIDTH, Main.GAME_HEIGHT);
    }
}
