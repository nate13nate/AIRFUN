package mainfiles;

import objs.Collider;
import objs.Entity;
import objs.Vector;

import java.util.ArrayList;

public class CollisionHandler {
  private ArrayList<Collider> colliders;

  public CollisionHandler(Entity[] entities) {
    colliders = new ArrayList<Collider>();

    for (Entity entity : entities) {
      colliders.add(entity.getCollider());
    }
  }

  public void start() {

  }

  public void loop(double deltaTime) {
    for (Collider collider : colliders) {
      for (Collider other : colliders) {
        if (collider.equals(other)) continue;

        Vector overlap = collider.overlaps(other);

        if (overlap.getX() == 0 && overlap.getY() == 0) continue;
        other.action(collider, overlap);
      }
    }
  }
}
