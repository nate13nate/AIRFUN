package colliders;

import objs.Collider;
import objs.Entity;
import objs.Vector;

public class EnemyCollider extends Collider {
  public EnemyCollider(Vector offset, Vector size, Entity parent) {
    super(offset, size, parent);
  }

  @Override
  public void action(Collider collider, Vector overlap) {
    collider.addForce(new Vector(-200, -30));

//    if (Math.abs(overlap.getX()) < Math.abs(overlap.getY())) {
//      collider.addToPosition(new Vector(overlap.getX(), 0));
//      collider.removeVelocity(new Vector(0, 1));
//    } else {
//      collider.addToPosition(new Vector(0, overlap.getY()));
//
//      if (overlap.getY() < 0 && collider.getVelocity().getY() > 0) {
//        collider.removeVelocity(new Vector(1, 0));
//      }
//      if (overlap.getY() > 0 && collider.getVelocity().getY() < 0)
//        collider.removeVelocity(new Vector(1, 0));
//    }
  }
}
