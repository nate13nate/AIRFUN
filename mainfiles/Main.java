package mainfiles;

import Entities.Platform;
import Entities.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.internal.util.xml.impl.Input;
import objs.Entity;
import objs.Vector;

import java.util.HashMap;

public class Main extends Application {
  // dimensions of game window in pixels
  public static int GAME_WIDTH = 800;
  public static int GAME_HEIGHT = 600;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    // set canvas and context
    Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
    GraphicsContext context = canvas.getGraphicsContext2D();

    // original input handler


    // original game scene
    GameScene scene = new GameScene(
        canvas,
        context,
        stage,
        GameScene.getDefaultInputHandler(),
        new Entity[]{
            new Player("images\\turtle.png", new Vector(100, 100), new Vector(5, 5), 10, 4, 450),
            new Platform("images\\turtle.png", new Vector(100, 300), new Vector(5, 5), 0)
        }
    );

    stage.setScene(scene.getScene());
    scene.start();

    stage.show();
  }
}
