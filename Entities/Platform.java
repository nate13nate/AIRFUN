package Entities;

import colliders.PlatformCollider;
import objs.Entity;
import objs.Vector;
import sprites.PlatformSprite;

import javax.xml.bind.annotation.XmlType;

public class Platform extends Entity {
  public Platform(String imageFileName, Vector position, Vector scale, double gravity, String[] imageFileNames, int length, boolean useThingy) {
    super(imageFileName, position, scale, gravity, useThingy);
    sprite = new PlatformSprite(imageFileName, position, length);
    sprite.setScale(scale);
    sprite.setImages(imageFileNames);

    collider = new PlatformCollider(new Vector(0, 0), new Vector(sprite.getImageDim().getX() * length, sprite.getImageDim().getY()), this);
  }

  @Override
  public void calcStart() {
    super.calcStart();

    collider.removeInteractableTag("DEFAULT");
    collider.removeTag("DEFAULT");
    collider.addTag("PLAYER");
  }
}
