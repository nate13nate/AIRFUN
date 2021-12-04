package Entities;

import colliders.SlowCollider;
import objs.Entity;
import objs.Vector;

public class Slow extends Entity {
  public Slow(String imageFileName, Vector position, Vector scale, double gravity) {
    super(imageFileName, position, scale, gravity, true);

    collider = new SlowCollider(new Vector(0, 0), sprite.getImageDim(), this);
  }

  @Override
  public void calcStart() {
    super.calcStart();

    collider.removeInteractableTag("DEFAULT");
    collider.removeTag("DEFAULT");
    collider.addTag("PLAYER");
    collider.addInteractableTag("SLOW");
  }
}
