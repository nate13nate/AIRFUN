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
import menu.SignInPage;
import objs.Entity;
import objs.Vector;
import menu.HighScorePage;

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
  public Scene signInPage = null;
  public boolean isSignedIn = false;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    VBox menu = new VBox();

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
            new Player("images\\turtle.png", new Vector(100, 100), new Vector(5, 5), 10, 7, 175),
            new Platform("images\\turtle.png", new Vector(-25, 0), new Vector(1, 50), 0, new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"}, 1, false),
            new Platform("images\\turtle.png", new Vector(805, 0), new Vector(1, 50), 0, new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"}, 1, false)
        }
    );

    // initialize buttons
    Button scoresButton = new Button();
    Button signInButton = new Button();
    Button backButton = new Button();
    Button backButton2 = new Button();
    Button startButton = new Button();

    //set text for buttons
    scoresButton.setText("View High Scores");
    signInButton.setText("Sign In");
    backButton.setText("Back Button");
    backButton2.setText("Back Button");
    startButton.setText("Start game");

    // create all the scene-swapping events for when you click the buttons
    scoresButton.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(scoresPage); }});
    signInButton.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(signInPage); }});
    backButton.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(menuScene); }});
    backButton2.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(menuScene); }});
    startButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        stage.setScene(gameScene.getScene());
        gameScene.start();
      }
    });

    menu.getChildren().addAll(startButton, scoresButton, signInButton);

    // start the menu
    signInPage = SignInPage.makeSignInPage(backButton);
    scoresPage = HighScorePage.makeScoresPage(backButton2);
    menuScene = new Scene(menu, GAME_WIDTH, GAME_HEIGHT);
    stage.setScene(menuScene);
    stage.show();
  }
}
