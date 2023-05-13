import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    try {
      DatabaseWorker worker = new DatabaseWorker();
      DirtyDatabaseWorker dirtyDatabaseWorker = new DirtyDatabaseWorker();

      worker.createTableFromObject("default_frames", new DefaultFrame(), true);
      worker.createTableFromObject("digital_frames", new DigitalFrame(), true);

      CSVFrameDataReader<DefaultFrame> defaultFrameCSVFrameDataReader = new CSVFrameDataReader<>(
          "default_frames.csv", ",", new DefaultFrame());
      CSVFrameDataReader<DigitalFrame> digitalFrameCSVFrameDataReader = new CSVFrameDataReader<>(
          "digital_frames.csv", ",", new DigitalFrame());

      ArrayList<DefaultFrame> defaultFrames = defaultFrameCSVFrameDataReader.readFrames();
      ArrayList<DigitalFrame> digitalFrames = digitalFrameCSVFrameDataReader.readFrames();

      worker.loadObjectsToBase(digitalFrames, "digital_frames");
      worker.loadObjectsToBase(defaultFrames, "default_frames");

      dirtyDatabaseWorker.dirtyReadAndRollback("SELECT * FROM digital_frames WHERE id = 961");
      Thread.sleep(150);
      worker.connection.createStatement().executeUpdate("DELETE FROM digital_frames WHERE id = 961");

      System.out.println(worker.getById(311, 2).toString().concat("\n"));

      for (Frame frame : worker.getAllObjects("digital_frames", 1)) {
        System.out.println(frame);
      }
    } catch (Exception e) {
      Journal.log(e.getMessage());
      System.out.println(e.getMessage());
      System.exit(-1);
    }
  }
}
