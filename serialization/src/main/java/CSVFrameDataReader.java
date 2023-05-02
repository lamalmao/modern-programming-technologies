import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFrameDataReader<T extends Frame> {

  private final T frameInstance;
  private final String fileName;
  private final BufferedReader reader;
  private final String delimiter;

  CSVFrameDataReader(String fileName, String delimiter, T frameInstance)
      throws FileNotFoundException {
    this.fileName = fileName;
    this.delimiter = delimiter;
    this.reader = new BufferedReader(new FileReader(fileName));
    this.frameInstance = frameInstance;
  }

  CSVFrameDataReader(T frameInstance) throws FileNotFoundException {
    this.fileName = "frames.csv";
    this.delimiter = ",";
    this.reader = new BufferedReader(new FileReader(this.fileName));
    this.frameInstance = frameInstance;
  }

  public ArrayList<T> readFrames() throws IOException {
    final String[] titles = this.reader.readLine().split(this.delimiter);

    ArrayList<T> result = new ArrayList<>();
    String data;
    int counter = 1;
    while ((data = this.reader.readLine()) != null) {
      try {
        String[] splitData = data.split(this.delimiter);
        T frame = (T) this.frameInstance.create();
        frame.parseCSVLine(splitData, titles);
        result.add(frame);
        Journal.log("Frame loaded - " + frame.id + ":" + frame.model);
      } catch (Exception e) {
        Journal.log("Parsing error, line " + counter + ". Message: " + e.getMessage());
      } finally {
        counter++;
      }
    }

    Journal.log(this.fileName + " successfully parsed. Got " + counter + " items");
    return result;
  }

  public String getFileName() {
    return this.fileName;
  }
}
