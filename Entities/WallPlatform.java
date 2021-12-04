package Entities;

import objs.Vector;

public class WallPlatform extends Platform {
  public WallPlatform(String imageFileName, Vector position, Vector scale, double gravity, String[] imageFileNames, int length, boolean useThingy) {
    super(imageFileName, position, scale, gravity, imageFileNames, length, useThingy);
  }

  @Override
  public void calcStart() {
    super.calcStart();

    collider.removeTag("SLOW");
  }
}
