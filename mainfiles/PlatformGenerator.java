package mainfiles;

import Entities.Platform;
import Entities.Slow;
import colliders.PlatformCollider;
import javafx.scene.canvas.GraphicsContext;
import objs.Vector;

import java.util.Date;
import java.util.Random;

public class PlatformGenerator {
  private Platform[] platforms;
  private GameScene scene;
  private int start = 0;
  private int length = 0;
  private Random rand = new Random(new Date().getTime());

  public PlatformGenerator(GameScene scene) {
    this.scene = scene;

    platforms = new Platform[7];
    platforms[0] = new Platform("images\\turtle.png", new Vector(100, 300), new Vector(5, 5), 0, new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"}, 5, true);
    platforms[1] = new Platform("images\\turtle.png", new Vector(700, 400), new Vector(5, 5), 0, new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"}, 2, true);
    platforms[2] = new Platform("images\\turtle.png", new Vector(900, 275), new Vector(5, 5), 0, new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"}, 7, true);

    platforms[0].setScene(scene);
    platforms[1].setScene(scene);
    platforms[2].setScene(scene);

    scene.addEntity(platforms[0]);
    scene.addEntity(platforms[1]);
    scene.addEntity(platforms[2]);

    length = 3;
  }

  public void start(GraphicsContext context) {
    for (int i = 0; i < platforms.length; i++) {
      if (platforms[i] == null) continue;
      platforms[i].calcStart();
      platforms[i].renderStart(context);
    }
  }

  public void loop(GraphicsContext context, double deltaTime) {
    if (platforms[(start + length - 1) % platforms.length].getPosition().getX() - scene.distanceMoved < 800) {
      int nextXAddition = rand.nextInt(400) + 10;
      int nextYAddition = nextXAddition > 210 ? 100 - rand.nextInt(300) : rand.nextInt(300) - 100;
      if (platforms[(start + length - 1) % platforms.length].getPosition().getY() == 100) {
        nextYAddition = Math.abs(nextYAddition);
      }
      else if (platforms[(start + length - 1) % platforms.length].getPosition().getY() == 450) {
        nextYAddition = -Math.abs(nextYAddition);
        nextXAddition = rand.nextInt(200) + 10;
      }
      double x = platforms[(start + length - 1) % platforms.length].getPosition().getX() + platforms[(start + length - 1) % platforms.length].getCollider().getWidth() + nextXAddition;
      double y = platforms[(start + length - 1) % platforms.length].getPosition().getY() + platforms[(start + length - 1) % platforms.length].getCollider().getHeight() + nextYAddition;
      if (y < 100) y = 100;
      if (y > 450) y = 450;
      if (length == platforms.length) {
        scene.removeEntity(platforms[(start + length) % platforms.length]);
        platforms[(start + length) % platforms.length] = new Platform(
            "images\\turtle.png",
            new Vector(
                x,
                y
            ),
            new Vector(5, 5),
            0,
            new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"},
            rand.nextInt(3) + 2,
            true
        );
        platforms[(start + length) % platforms.length].setScene(scene);
        platforms[(start + length) % platforms.length].calcStart();
        platforms[(start + length) % platforms.length].renderStart(context);
        scene.addEntity(platforms[(start + length) % platforms.length]);
        start = (start + 1) % platforms.length;
      } else {
        platforms[(start + length) % platforms.length] = new Platform(
            "images\\turtle.png",
            new Vector(x, y),
            new Vector(5, 5),
            0,
            new String[] {"images\\platformLeftEnd.png", "images\\platform.png", "images\\platformRightEnd.png"},
            rand.nextInt(3) + 2,
            true
        );
        platforms[(start + length) % platforms.length].setScene(scene);
        platforms[(start + length) % platforms.length].calcStart();
        platforms[(start + length) % platforms.length].renderStart(context);
        scene.addEntity(platforms[(start + length) % platforms.length]);
        length++;
      }

      if (rand.nextInt(20) == 0) {
        Slow slow = new Slow(
            "images\\slowdownthing.png",
            new Vector(x + 50, y - 20),
            new Vector(5, 5),
            10
        );
        slow.setScene(scene);
        slow.calcStart();
        slow.renderStart(context);
        scene.addEntity(slow);
      }
    }
    for (int i = 0; i < platforms.length; i++) {
      if (platforms[i] == null) continue;
      platforms[i].calcLoop(deltaTime);
      platforms[i].renderLoop(context, deltaTime);
    }
  }
}
