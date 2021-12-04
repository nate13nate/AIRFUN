package Entities;

import colliders.EnemyCollider;
import objs.Entity;
import objs.Vector;

public class Enemy extends Entity {
  private double speed;

  public Enemy(String imageFileName, Vector position, Vector scale, double gravity, double speed) {
    super(imageFileName, position, scale, gravity, false);
    this.speed = speed;

    collider = new EnemyCollider(new Vector(0, 0), sprite.getImageDim(), this);
  }

  @Override
  public void calcStart() {
    super.calcStart();

    collider.removeInteractableTag("DEFAULT");
    collider.removeTag("DEFAULT");
    collider.addTag("PLAYER");

    setVelocity(new Vector(speed, 0));
  }
}
