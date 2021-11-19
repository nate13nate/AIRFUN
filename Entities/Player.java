package Entities;

import objs.Entity;
import objs.Vector;

public class Player extends Entity {
  protected double speed;
  protected double jumpForce;
  protected boolean canJump;

  public Player(String imageFileName, Vector position, Vector scale, double gravity, double speed, double jumpForce) {
    super(imageFileName, position, scale, gravity);
    this.speed = speed;
    this.jumpForce = jumpForce;
  }

  @Override
  public void calcStart() {
    super.calcStart();

    collider.removeTag("DEFAULT");
  }

  @Override
  public void calcLoop(double deltaTime) {
    boolean left = scene.getInputValue("LEFT") == 1;
    boolean right = scene.getInputValue("RIGHT") == 1;
    double horiz = 0;
    double verti = 0;

    if (!(left && right) && left) horiz = -speed;
    else if (!(left && right) && right) horiz = speed;

    if (scene.getInputValue("ACTION") == 1 && canJump) {
      verti = -jumpForce;
      canJump = false;
    }

    setVelocity(new Vector(horiz, velocity.getVelocity().getY()));
    velocity.addForce(new Vector(0, verti));

    super.calcLoop(deltaTime);
  }

  public void setCanJump(boolean canJump) {
    this.canJump = canJump;
  }
}
