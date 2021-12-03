package objs;

public class Vector {
  private double x;
  private double y;

  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Vector add(double n) {
    x += n;
    y += n;
    return this;
  }

  public Vector add(Vector n) {
    x += n.getX();
    y += n.getY();
    return this;
  }

  public Vector sub(double n) {
    x -= n;
    y -= n;
    return this;
  }

  public Vector sub(Vector n) {
    x -= n.getX();
    y -= n.getY();
    return this;
  }

  public Vector multiply(double n) {
    x *= n;
    y *= n;
    return this;
  }

  public Vector multiply(double n, boolean keepChanges) {
    if (keepChanges) return multiply(n);
    return new Vector(x * n, y * n);
  }

  public Vector divide(double n) {
    x /= n;
    y /= n;
    return this;
  }

  public Vector divide(double n, boolean keepChanges) {
    if (keepChanges) return divide(n);
    return new Vector(x / n, y / n);
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }
}
