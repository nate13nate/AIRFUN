package colliders;

import objs.Collider;
import objs.Entity;
import objs.Vector;

import java.util.ArrayList;

public class PlatformCollider extends Collider {
  public PlatformCollider(double x, double y, double width, double height, Entity parent) {
    super(x, y, width, height, parent);
  }

  public PlatformCollider(Vector offset, Vector size, Entity parent) {
    super(offset, size, parent);
  }

  @Override
  public void action(Collider collider, Vector overlap) {
    if (Math.abs(overlap.getX()) < Math.abs(overlap.getY())) {
      collider.addToPosition(new Vector(overlap.getX(), 0));
      collider.removeVelocity(new Vector(0, 1));
    } else {
      collider.addToPosition(new Vector(0, overlap.getY()));

      if (overlap.getY() < 0 && collider.getVelocity().getY() > 0) {
        collider.removeVelocity(new Vector(1, 0));
        collider.setCanJump();
      }
      if (overlap.getY() > 0 && collider.getVelocity().getY() < 0)
        collider.removeVelocity(new Vector(1, 0));
    }
  }
}
