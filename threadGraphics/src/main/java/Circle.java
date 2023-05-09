import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Timer;
import java.util.TimerTask;


public class Circle {

  private final int radius;
  private final Color color;
  private double vx;
  private double vy;
  private double x;
  private double y;
  private boolean locked = false;
  private GraphicsPanel parent;

  public Circle(int radius, Color color, double x, double y, double vx, double vy) {
    this.radius = radius;
    this.color = color;
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  public Color getColor() {
    return color;
  }

  public Ellipse2D getShape() {
    return new Ellipse2D.Double(x, y, radius, radius);
  }

  public void setParent(GraphicsPanel parent) {
    this.parent = parent;
  }

  public void reflect() {
    vx *= -1;
    vy *= -1;
  }

  public int getRadius() {
    return radius;
  }

  public void checkBordersIntersect() {
    double nextCenterX = getXCenter() + vx;
    double nextCenterY = getYCenter() + vy;

    if (nextCenterX - radius <= 0 || nextCenterX + radius >= parent.getWidth()) {
      vx *= -1;
    }

    if (nextCenterY - radius <= 0 || nextCenterY + radius >= parent.getHeight()) {
      vy *= -1;
    }
  }

  public double getXCenter() {
    return x + radius;
  }

  public double getYCenter() {
    return y + radius;
  }

  public void calculateStep() {
    y += vy;
    x += vx;
  }

  public void lock() {
    this.locked = true;
  }

  public void unlock() {
    this.locked = false;
  }

  public void run() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        if (!locked) {
          checkBordersIntersect();
          calculateStep();
          lock();
        }
      }
    }, 0, 15);
  }
}
