package Entities;

import colliders.PlatformCollider;
import objs.Entity;
import objs.Vector;

public class Platform extends Entity {
  public Platform(String imageFileName, Vector position, Vector scale, double gravity) {
    super(imageFileName, position, scale, gravity);

    collider = new PlatformCollider(new Vector(0, 0), sprite.getImageDim(), this);
  }
}
