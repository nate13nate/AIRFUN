package mainfiles;

import Entities.Enemy;
import Entities.Platform;
import javafx.scene.canvas.GraphicsContext;
import objs.Vector;

import java.util.Date;
import java.util.Random;

public class EnemyGenerator {
  private Enemy enemy;
  private GameScene scene;
  private int length = 0;
  private double time = 0;
  private Random rand = new Random(new Date().getTime());

  public EnemyGenerator(GameScene scene) {
    this.scene = scene;

    enemy = null;
  }

  public void start(GraphicsContext context) {
    if (enemy == null) return;
    enemy.calcStart();
    enemy.renderStart(context);
  }

  public void loop(GraphicsContext context, double deltaTime) {
    if (enemy != null && enemy.getPosition().getX() < -200) {
      length = 0;
      scene.removeEntity(enemy);
      enemy = null;
    } else if (enemy != null) {
      enemy.calcLoop(deltaTime);
      enemy.renderLoop(context, deltaTime);
    }

    time += deltaTime;
    if (time > 10) {
      if (length > 0) return;
      time = 0;
      double x = 900;
      double y = rand.nextInt(300) + 100;

      double gravitySign = rand.nextInt(2) == 0 ? 1 : -1;

      enemy = new Enemy(
          "images\\birb.png",
          new Vector(x, y),
          new Vector(5, 5),
          rand.nextDouble() * gravitySign,
          rand.nextInt(5) - 9
      );
      enemy.setScene(scene);
      enemy.calcStart();
      enemy.renderStart(context);
      scene.addEntity(enemy);
      length = 1;
    }
  }
}
