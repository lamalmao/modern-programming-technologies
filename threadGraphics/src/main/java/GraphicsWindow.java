import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

public class GraphicsWindow extends JFrame {

  private final ArrayList<Circle> figures;
  private final GraphicsPanel panel;

  public GraphicsWindow(int height, int width, Color background, ArrayList<Circle> figures) {
    super();

    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
      }
    });

    this.figures = figures;
    this.setSize(width, height);
    this.setResizable(false);

    GraphicsPanel panel = new GraphicsPanel(this);
    this.panel = panel;

    this.setBackground(background);
    this.add(panel);
    this.setVisible(true);
  }

  public void runThreads() {
    for (Circle figure : figures) {
      figure.setParent(panel);
      figure.run();
    }
  }

  public ArrayList<Circle> getFigures() {
    return figures;
  }
}
