import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.List;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel {

  private boolean started = false;
  private final GraphicsWindow parentWindow;

  public GraphicsPanel(GraphicsWindow window) {
    this.setBounds(0, 0, window.getWidth(), window.getHeight());
    parentWindow = window;
    this.setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    Graphics2D g2d = (Graphics2D) graphics;

    Circle figure;
    List<Circle> figures = parentWindow.getFigures();
    int size = figures.size();
    Ellipse2D shape;

    if (!started) {
      started = true;
      parentWindow.runThreads();
    }

    for (int i = 0; i < size; i++) {
      figure = figures.get(i);
      shape = figure.getShape();

      for (int j = 0; j < size; j++) {
        if (j == i) {
          continue;
        }
        Circle neighbour = figures.get(j);
        double distance = Math.sqrt(
            Math.pow(figure.getXCenter() - neighbour.getXCenter(), 2) + Math.pow(
                figure.getYCenter() - neighbour.getYCenter(), 2));

        if (distance <= figure.getRadius() || distance <= neighbour.getRadius()) {
          figure.reflect();
          neighbour.reflect();

          figure.calculateStep();
          neighbour.calculateStep();
        }
      }

      g2d.setColor(figure.getColor());
      g2d.fill(shape);

      figure.unlock();
    }

    repaint();
  }
}
