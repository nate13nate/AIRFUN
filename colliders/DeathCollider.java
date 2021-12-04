package colliders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objs.Collider;
import objs.Entity;
import objs.Vector;

public class DeathCollider extends Collider {
  public DeathCollider(Vector offset, Vector size, Entity parent) {
    super(offset, size, parent);
  }

  @Override
  public void action(Collider collider, Vector overlap) {
    parent.scene.setDead(true);
  }
}
