package mainfiles;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objs.Entity;
import objs.Sprite;
import objs.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

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

  private PlatformGenerator platformGenerator;
  private EnemyGenerator enemyGenerator;

  private LinkedList<Entity> entities;

  private Sprite background;

  private Canvas canvas;
  private GraphicsContext context;

  public double distanceMoved = -200;
  public double points = distanceMoved + 200;
  public double increaseAmount = 4;

  private boolean dead = false;

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

    this.entities = new LinkedList<Entity>(Arrays.asList(entities));
    for (int i = 0; i < entities.length; i++) {
      entities[i].setScene(this);
    }

    collisionHandler = new CollisionHandler(this.entities);

    borderPane = new BorderPane(canvas);
    scene = new Scene(borderPane);

    platformGenerator = new PlatformGenerator(this);
    enemyGenerator = new EnemyGenerator(this);
  }

  public void start() {
    background = new Sprite("images\\background.png", new Vector(0, 0));
    background.setScale(new Vector(5, 5));
    background.render(context);

    // run start for program segments
    platformGenerator.start(context);
    enemyGenerator.start(context);
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

        distanceMoved += increaseAmount;
        if (!dead) points = distanceMoved + 200;
        increaseAmount += deltaTime * .07;

        background.render(context);

        // run loop for different program segments
        platformGenerator.loop(context, deltaTime);
        enemyGenerator.loop(context, deltaTime);
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
  public void changeScene(Scene scene) {
    // start other scene
    stage.setScene(scene);

    // stop this scene
    gameLoop.stop();
  }

  public Scene getScene() {
    return scene;
  }

  public int getInputValue(String key) {
    return inputHandler.getInputValue(key);
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
    collisionHandler.addCollider(entity);
  }

  public void removeEntity(Entity entity) {
    entities.remove(entity);
    collisionHandler.removeCollider(entity);
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }
}
