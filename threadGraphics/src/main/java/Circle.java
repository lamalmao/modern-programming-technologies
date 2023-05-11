import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Timer;
import java.util.TimerTask;


public class Circle {

  private final int diameter;
  private final Color color;
  private double vx;
  private double vy;
  private double x;
  private double y;
  private boolean locked = false;
  private GraphicsPanel parent;

  public Circle(int diameter, Color color, double x, double y, double vx, double vy) {
    this.diameter = diameter;
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
    return new Ellipse2D.Double(x, y, diameter, diameter);
  }

  public void setParent(GraphicsPanel parent) {
    this.parent = parent;
  }

  public void reflect() {
    vx *= -1;
    vy *= -1;
  }

  public int getDiameter() {
    return diameter;
  }

  public void checkBordersIntersect() {
    double nextCenterX = getXCenter() + vx;
    double nextCenterY = getYCenter() + vy;

    if (nextCenterX - diameter <= 0 || nextCenterX + diameter >= parent.getWidth()) {
      vx *= -1;
    }

    if (nextCenterY - diameter <= 0 || nextCenterY + diameter >= parent.getHeight()) {
      vy *= -1;
    }
  }

  public double getXCenter() {
    return x + diameter / 2;
  }

  public double getYCenter() {
    return y + diameter / 2;
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
