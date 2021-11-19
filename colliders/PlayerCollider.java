package colliders;

import Entities.Player;
import objs.Collider;
import objs.Entity;
import objs.Vector;

public class PlayerCollider extends Collider {
  public PlayerCollider(double x, double y, double width, double height, Player parent) {
    super(x, y, width, height, parent);
  }

  public PlayerCollider(Vector offset, Vector size, Player parent) {
    super(offset, size, parent);
  }
}
