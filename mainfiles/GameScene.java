package mainfiles;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objs.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class GameScene {
  public static InputHandler getDefaultInputHandler() {
    HashMap<String, String[]> inputKeyCodes = new HashMap<String, String[]>();
    inputKeyCodes.put("LEFT", new String[]{"LEFT", "A"});
    inputKeyCodes.put("RIGHT", new String[]{"RIGHT", "D"});
    inputKeyCodes.put("ACTION", new String[]{"SPACE", "Z", "COMMA"});

    return new InputHandler(inputKeyCodes);
  }

  private BorderPane borderPane;
  private Scene scene;
  private Stage stage;

  private AnimationTimer gameLoop;

  private InputHandler inputHandler;
  private CollisionHandler collisionHandler;

  private Entity[] entities;

  private Canvas canvas;
  private GraphicsContext context;

  public GameScene(
      Canvas canvas,
      GraphicsContext context,
      Stage stage,
      InputHandler inputHandler,
      Entity[] entities
  ) {
    this.canvas = canvas;
    this.context = context;
    this.stage = stage;

    this.inputHandler = inputHandler;
    this.inputHandler.setScene(this);

    this.entities = entities;
    for (int i = 0; i < entities.length; i++) {
      entities[i].setScene(this);
    }

    collisionHandler = new CollisionHandler(entities);

    borderPane = new BorderPane(canvas);
    scene = new Scene(borderPane);
  }

  public void start() {
    // run start for program segments
    inputHandler.start();
    for (Entity entity : entities) {
      entity.calcStart();
      entity.renderStart(context);
    }
    collisionHandler.start();

    gameLoop = new AnimationTimer() {
      double previous = -1;

      @Override
      public void handle(long nowLong) {
        // calculate delta time
        double now = nowLong * .000000001;
        double deltaTime = previous > 0 ? now - previous : 1/60.0;
        previous = now;

        // run loop for different program segments
        inputHandler.loop(deltaTime);
        for (Entity entity : entities) {
          entity.calcLoop(deltaTime);
          entity.renderLoop(context, deltaTime);
        }
        collisionHandler.loop(deltaTime);
      }
    };

    gameLoop.start();
  }

  // needs to be tested
  public void changeScene(GameScene scene) {
    // start other scene
    stage.setScene(scene.getScene());
    scene.start();

    // stop this scene
    gameLoop.stop();
  }

  public Scene getScene() {
    return scene;
  }

  public int getInputValue(String key) {
    return inputHandler.getInputValue(key);
  }
}
