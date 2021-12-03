package sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import objs.Sprite;
import objs.Vector;

public class PlatformSprite extends Sprite {
  private Image[] images;
  private String[] filenames;
  private int length;

  public PlatformSprite(String filename, Vector position, int length) {
    super(filename, position);

    images = new Image[3];
    filenames = new String[3];
    this.length = length;
  }

  @Override
  public void render(GraphicsContext context) {
    images[0] = new Image(
        filenames[0],
        size.getX() * scale.getX(),
        size.getY() * scale.getY(),
        false,
        false
    );

    images[1] = new Image(
        filenames[1],
        size.getX() * scale.getX(),
        size.getY() * scale.getY(),
        false,
        false
    );

    images[2] = new Image(
        filenames[2],
        size.getX() * scale.getX(),
        size.getY() * scale.getY(),
        false,
        false
    );

    context.drawImage(
        images[0],
        position.getX(),
        position.getY(),
        image.getWidth(),
        image.getHeight()
    );

    for (int i = 1; i < length - 1; i++) {
      context.drawImage(
          images[1],
          position.getX() + (image.getWidth() * i),
          position.getY(),
          image.getWidth(),
          image.getHeight()
      );
    }

    context.drawImage(
        images[2],
        position.getX() + (image.getWidth() * (length - 1)),
        position.getY(),
        image.getWidth(),
        image.getHeight()
    );
  }

  public void setImages(String[] filenames) {
    this.filenames = filenames;

    for (int i = 0; i < filenames.length; i++) {
      images[i] = new Image(filenames[i]);
    }
  }
}
