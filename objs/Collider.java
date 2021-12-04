package objs;

import java.util.ArrayList;
import java.util.Date;

public class Collider {
  protected Vector offset;
  protected Vector size;
  protected Entity parent;
  protected ArrayList<String> tags;
  protected ArrayList<String> interactableTags;

  protected boolean isTrigger;

  public Collider(double x, double y, double width, double height, Entity parent) {
    this.offset = new Vector(x, y);
    this.size = new Vector(width, height);
    this.parent = parent;

    tags = new ArrayList<String>();
    interactableTags = new ArrayList<String>();
  }

  public Collider(Vector offset, Vector size, Entity parent) {
    this.offset = offset;
    this.size = size;
    this.parent = parent;

    tags = new ArrayList<String>();
    interactableTags = new ArrayList<String>();
  }

  public Vector overlaps(Collider other) {
    boolean canInteract = false;
    for (String tag : interactableTags) {
      for (String otherTag : other.tags) {
        if (tag.equals(otherTag)) {
          canInteract = true;
          break;
        }
      }
    }

    if (!canInteract) return new Vector(0, 0);

    double x = getX();
    double y = getY();
    double width = getWidth();
    double height = getHeight();

    double otherX = other.getX();
    double otherY = other.getY();
    double otherWidth= other.getWidth();
    double otherHeight = other.getHeight();

//    System.out.printf("collider: %f %f\nother: %f %f\n\n", x, y, otherX, otherY);

    boolean noOverlap =
        x + width < otherX ||
            x > otherX + otherWidth ||
            y + height < otherY ||
            y > otherY + otherHeight;

    if (noOverlap) return new Vector(0, 0);

    double overlapX = 0;
    double overlapY = 0;

    if (x + (width / 2.0) < otherX + (otherWidth / 2.0)) overlapX = -((x + width) - otherX);
    else overlapX = (otherX + otherWidth) - x;

    if (y + (height / 2.0) < otherY + (otherHeight / 2.0)) overlapY = -((y + height) - otherY);
    else overlapY = (otherY + otherHeight) - y;

//    System.out.printf("colliding\n\n");
    return new Vector(overlapX, overlapY);
  }

  public void addTag(String tag) {
    tags.add(tag);
  }

  public void removeTag(String tag) {
    tags.remove(tag);
  }

  public void addInteractableTag(String tag) {
    interactableTags.add(tag);
  }

  public void removeInteractableTag(String tag) {
    interactableTags.remove(tag);
  }

  public void action(Collider collider, Vector overlap) {

  }

  public void setCanJump() {
    parent.setCanJump(true);
  }

  public double getX() {
    return offset.getX() + parent.getPosition().getX() + (parent.useThingy ? 0 : parent.scene.distanceMoved);
  }

  public double getY() {
    return offset.getY() + parent.getPosition().getY();
  }

  public double getWidth() {
    return size.getX();
  }

  public double getHeight() {
    return size.getY();
  }

  public void setTrigger(boolean isTrigger) {
    this.isTrigger = isTrigger;
  }

  public boolean getIsTrigger() {
    return isTrigger;
  }

  public void addToPosition(Vector addition) {
    parent.setPosition(parent.getPosition().add(addition));
  }

  public Vector getVelocity() {
    return parent.getVelocity().getVelocity();
  }

  public void addForce(Vector force) {
    parent.getVelocity().addForce(force);
  }

  public void removeVelocity(Vector velocity) {
    Vector parentVelocity = parent.getVelocity().getVelocity();
    double x = velocity.getX() == 0 ? 0 : parentVelocity.getX();
    double y = velocity.getY() == 0 ? 0 : parentVelocity.getY();

    parent.setVelocity(new Vector(x, y));
  }
}
