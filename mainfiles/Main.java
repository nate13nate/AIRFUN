package mainfiles;

import Entities.Platform;
import Entities.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.internal.util.xml.impl.Input;
import objs.Entity;
import objs.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import database.User;
import database.Database;
import menu.ScoresTable;

public class Main extends Application {
  // dimensions of game window in pixels
  public static int GAME_WIDTH = 800;
  public static int GAME_HEIGHT = 600;
  public Scene menuScene = null;
  public Scene scoresPage = null;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    VBox menu = new VBox();
    VBox highScores = new VBox();

    // set canvas and context
    Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
    GraphicsContext context = canvas.getGraphicsContext2D();

    // original input handler

    // original game scene
    GameScene gameScene = new GameScene(
        canvas,
        context,
        stage,
        GameScene.getDefaultInputHandler(),
        new Entity[]{
            new Player("images\\turtle.png", new Vector(100, 100), new Vector(5, 5), 10, 4, 450),
            new Platform("images\\turtle.png", new Vector(100, 300), new Vector(5, 5), 0)
        }
    );

    // button that takes you to the high scores page
    Button scoresButton = new Button();
    scoresButton.setText("View High Scores");
    scoresButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        stage.setScene(scoresPage);
      }
    });

    // back button
    Button backButton = new Button();
    backButton.setText("Back Button");
    backButton.setOnAction(new EventHandler<ActionEvent>() {

      // when the button is clicked, we swap the menu scene out for the game scene
      @Override
      public void handle(ActionEvent event) {
        stage.setScene(menuScene);
      }
    });

    // high scores page
    ArrayList<User> users = new ArrayList<>();
    users = Database.getHighScores();

    TableView<User> table = new TableView<>();
    ScoresTable scoresTable = new ScoresTable();
    table = scoresTable.makeTable(users);

    highScores.getChildren().addAll(table, backButton);
    scoresPage = new Scene(highScores, 300, 300);

    // button that starts the game
    Button startButton = new Button();
    startButton.setText("Start game");
    startButton.setOnAction(new EventHandler<ActionEvent>() {

      // when the button is clicked, we swap the menu scene out for the game scene
      @Override
      public void handle(ActionEvent event) {
        stage.setScene(gameScene.getScene());
        gameScene.start();
      }
    });

    // menu scene
    menu.getChildren().addAll(startButton, scoresButton);

    // start the menu
    menuScene = new Scene(menu, 300, 300);
    stage.setScene(menuScene);
    stage.show();
  }
}
