import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVDataReader<T extends CSVReadable<?>> {

  private final T targetInstance;
  private final String fileName;
  private final BufferedReader reader;
  private final String delimiter;

  CSVDataReader(String fileName, String delimiter, T objectInstance)
      throws FileNotFoundException {
    this.fileName = fileName;
    this.delimiter = delimiter;
    this.reader = new BufferedReader(new FileReader(fileName));
    this.targetInstance = objectInstance;
  }

  CSVDataReader(T targetInstance) throws FileNotFoundException {
    this.fileName = "frames.csv";
    this.delimiter = ",";
    this.reader = new BufferedReader(new FileReader(this.fileName));
    this.targetInstance = targetInstance;
  }

  public ArrayList<T> readFile() throws IOException {
    final String[] titles = this.reader.readLine().split(this.delimiter);

    ArrayList<T> result = new ArrayList<>();
    String data;
    int counter = 1;
    while ((data = this.reader.readLine()) != null) {
      try {
        String[] splitData = data.split(this.delimiter);
        T resultObject = (T) this.targetInstance.create();
        resultObject.parseCSVLine(splitData, titles);
        result.add(resultObject);
        Journal.log("Object loaded " + resultObject);
      } catch (Exception e) {
        Journal.log("Parsing error, line " + counter + ". Message: " + e.getMessage() + " " + e.getCause());
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
