import java.io.FileWriter;
import java.io.IOException;

public abstract class Journal {

  private static final FileWriter writer;
  private static final String lineSeparator = System.lineSeparator();

  static {
    try {
      writer = new FileWriter("journal.txt", true);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void log(String text) throws IOException {
    writer.write(text);
    writer.write(Journal.lineSeparator);
    writer.flush();
  }
}
