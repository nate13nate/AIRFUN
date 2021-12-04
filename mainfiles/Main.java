package mainfiles;

import Entities.Death;
import Entities.Enemy;
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
import menu.SignUpPage;
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
  public Scene signUpPage = null;
  public Scene signInPage = null;
  public static boolean isSignedIn = false;
  public String id;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    VBox menu = new VBox();
    ArrayList<User> users = Database.getHighScores();

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
            new Platform("images\\turtle.png", new Vector(-25, -500), new Vector(1, 200), 0, new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"}, 1, false),
            new Platform("images\\turtle.png", new Vector(805, -500), new Vector(1, 200), 0, new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"}, 1, false),
            new Death("images\\turtle.png", new Vector(0, 650), new Vector(50, 1)),
        }
    );

    // initialize buttons
    Button scoresButton = new Button();
    Button signUpButton = new Button();
    Button signInButton = new Button();
    Button backButton = new Button();
    Button backButton2 = new Button();
    Button backButton3 = new Button();
    Button startButton = new Button();

    //set text for buttons
    scoresButton.setText("View High Scores");
    signUpButton.setText("Sign Up");
    signInButton.setText("Sign In");
    backButton.setText("Back Button");
    backButton2.setText("Back Button");
    backButton3.setText("Back Button");
    startButton.setText("Start game");

    // create all the scene-swapping events for when you click the buttons
    scoresButton.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(scoresPage); }});
    signUpButton.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(signUpPage); }});
    signInButton.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(signInPage); }});
    backButton.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(menuScene); }});
    backButton2.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(menuScene); }});
    backButton3.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) { stage.setScene(menuScene); }});
    startButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        stage.setScene(gameScene.getScene());
        gameScene.start();
      }
    });

    menu.getChildren().addAll(startButton, scoresButton, signUpButton, signInButton);

    // start the menu
    signUpPage = SignUpPage.makeSignUpPage(backButton, users);
    signInPage = SignInPage.makeSignInPage(backButton2, users);
    scoresPage = HighScorePage.makeScoresPage(backButton3, users);
    menuScene = new Scene(menu, GAME_WIDTH, GAME_HEIGHT);
    stage.setScene(menuScene);
    stage.show();
  }
}
