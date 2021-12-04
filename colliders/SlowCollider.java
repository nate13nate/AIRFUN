package colliders;

import objs.Collider;
import objs.Entity;
import objs.Vector;

public class SlowCollider extends Collider {
  public SlowCollider(Vector offset, Vector size, Entity parent) {
    super(offset, size, parent);
  }

  @Override
  public void action(Collider other, Vector overlap) {
    parent.scene.increaseAmount *= .75;
    parent.scene.removeEntity(parent);
  }
}
