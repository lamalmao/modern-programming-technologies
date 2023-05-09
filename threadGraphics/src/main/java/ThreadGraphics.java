import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class ThreadGraphics {

  public static void main(String[] args) {
    new GraphicsWindow(700, 1000, Color.WHITE, new ArrayList<>(Arrays.asList(
        new Circle(40, Color.BLUE, 65, 150, 2, 1.6),
        new Circle(15, Color.RED, 150, 270, 2.9, 1.6),
        new Circle(72, Color.GREEN, 300, 420, 2, 1),
        new Circle(100, Color.PINK, 700, 10, 3, -1),
        new Circle(127, Color.YELLOW, 450, 70, -3.5, -0.6),
        new Circle(50, Color.BLACK, 150, 150, -7, 5)))
    );
  }
}
