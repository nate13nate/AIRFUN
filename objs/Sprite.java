package objs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
  protected Image image;
  protected Vector scale;
  protected Vector size;
  protected Vector position;
  protected String filename;

  public Sprite(String filename, Vector position) {
    this.filename = filename;
    image = new Image(filename);
    this.position = position;
    scale = new Vector(1, 1);
    size = new Vector(image.getWidth(), image.getHeight());
  }

  public void render(GraphicsContext context) {
    image = new Image(
        filename,
        size.getX() * scale.getX(),
        size.getY() * scale.getY(),
        false,
        false
    );

    context.drawImage(
        image,
        position.getX(),
        position.getY(),
        image.getWidth(),
        image.getHeight()
    );
  }

  public void setScale(Vector scale) {
    this.scale = scale;
    image = new Image(
        filename,
        size.getX() * scale.getX(),
        size.getY() * scale.getY(),
        false,
        false
    );
  }

  public void setImages(String[] filenames) {}

  public Vector getScale() {
    return scale;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public Vector getImageDim() {
    return new Vector(image.getWidth(), image.getHeight());
  }
}
