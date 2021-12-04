package objs;

import javafx.scene.canvas.GraphicsContext;
import mainfiles.GameScene;

public class Entity {
  protected Collider collider;
  protected Sprite sprite;
  protected Vector position;
  protected Velocity velocity;
  public GameScene scene;
  protected boolean useThingy;

  public Entity(String imageFileName, Vector position, Vector scale, double gravity, boolean useThingy) {
    sprite = new Sprite(imageFileName, position);
    sprite.setScale(scale);

    this.useThingy = useThingy;

    this.position = position;

    collider = new Collider(new Vector(0, 0), sprite.getImageDim(), this);
    velocity = new Velocity(new Vector(0, gravity), this);
  }

  public void calcStart() {
    velocity.calcStart();

    collider.addTag("DEFAULT");
    collider.addInteractableTag("DEFAULT");
  }

  public void calcLoop(double deltaTime) {
    velocity.calcLoop(deltaTime);
  }

  public void renderStart(GraphicsContext context) {
    velocity.renderStart();
    renderImage(context);
  }

  public void renderLoop(GraphicsContext context, double deltaTime) {
    velocity.renderLoop(deltaTime);
    renderImage(context);
  }

  private void renderImage(GraphicsContext context) {
    sprite.setPosition(new Vector(useThingy ? position.getX() - scene.distanceMoved : position.getX(), position.getY()));
    sprite.render(context);
  }

  public void setCanJump(boolean canJump) {

  }

  public void setScene(GameScene scene) {
    this.scene = scene;
  }

  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public Collider getCollider() {
    return collider;
  }

  public Velocity getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector velocity) {
    this.velocity.setVelocity(velocity);
  }
}
