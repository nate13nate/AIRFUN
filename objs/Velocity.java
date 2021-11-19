package objs;

public class Velocity {
  private Vector velocity;
  private Vector force;
  private Vector continuousForce;

  private Vector gravityForce;

  private Entity entity;

  public Velocity(Vector gravityForce, Entity entity) {
    velocity = new Vector(0, 0);
    force = new Vector(0, 0);
    continuousForce = new Vector(0, 0);

    this.gravityForce = gravityForce;
    this.entity = entity;
  }

  public void calcStart() {

  }

  public void calcLoop(double deltaTime) {
    velocity.add(force.multiply(deltaTime, false));
    force.divide(2).multiply(deltaTime);

    velocity.add(continuousForce.multiply(deltaTime, false));

    velocity.add(gravityForce.multiply(deltaTime, false));
    entity.setPosition(entity.getPosition().add(velocity));
  }

  public void renderStart() {

  }

  public void renderLoop(double deltaTime) {

  }

  public Vector getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

  public void addForce(Vector force) {
    this.force.add(force);
  }
}
