import java.io.IOException;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    try {
      DatabaseWorker worker = new DatabaseWorker();
      worker.createTableFromObject("default_frames", new DefaultFrame(), true);
      worker.createTableFromObject("digital_frames", new DigitalFrame(), true);

      CSVFrameDataReader<DefaultFrame> defaultFrameCSVFrameDataReader = new CSVFrameDataReader<>(
          "default_frames.csv", ",", new DefaultFrame());
      CSVFrameDataReader<DigitalFrame> digitalFrameCSVFrameDataReader = new CSVFrameDataReader<>(
          "digital_frames.csv", ",", new DigitalFrame());

      ArrayList<DefaultFrame> defaultFrames = defaultFrameCSVFrameDataReader.readFrames();
      ArrayList<DigitalFrame> digitalFrames = digitalFrameCSVFrameDataReader.readFrames();

      worker.loadObjectsToBase(defaultFrames, "default_frames");
      worker.loadObjectsToBase(digitalFrames, "digital_frames");

      worker.dirtyConnection("default_frames", "DELETE FROM default_frames WHERE id = 206",
          new DefaultFrame()).join();

    } catch (Exception e) {
      Journal.log(e.getMessage());
      System.out.println(e.getMessage());
      System.exit(-1);
    }
  }
}
