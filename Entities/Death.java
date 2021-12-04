package Entities;

import colliders.DeathCollider;
import objs.Entity;
import objs.Vector;

public class Death extends Entity {
  public Death(String imageFileName, Vector position, Vector scale) {
    super(imageFileName, position, scale, 0, false);

    collider = new DeathCollider(new Vector(0, 0), sprite.getImageDim(), this);
  }

  @Override
  public void calcStart() {
    super.calcStart();

    collider.removeInteractableTag("DEFAULT");
    collider.removeTag("DEFAULT");
    collider.addTag("PLAYER");
  }
}
