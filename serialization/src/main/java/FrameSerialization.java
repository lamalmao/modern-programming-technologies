import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FrameSerialization {

  public static void main(String[] args) throws IOException {
    try {
      final CSVFrameDataReader<DefaultFrame> defaultFrameCSVFrameDataReader = new CSVFrameDataReader<>(
          "default_frames.csv", ",", new DefaultFrame());
      final CSVFrameDataReader<DigitalFrame> digitalFrameCSVFrameDataReader = new CSVFrameDataReader<>(
          "digital_frames.csv", ",", new DigitalFrame());

      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
          new FileOutputStream("frames.dat"));

      final String defaultFramesFileName = defaultFrameCSVFrameDataReader.getFileName();
      final ArrayList<DefaultFrame> defaultFrames = defaultFrameCSVFrameDataReader.readFrames();
      System.out.println(defaultFramesFileName + ":");
      System.out.printf("%-5s %-20s %-8s %-2s %-2s %-10s %-10s%n", "id", "model", "price", "width",
          "height", "material", "shape");
      for (Object frame : defaultFrames.toArray()) {
        objectOutputStream.writeObject(frame);
        System.out.println(frame.toString());
      }
      Journal.log(defaultFramesFileName + " data serialized to frames.dat");

      final String digitalFramesFileName = digitalFrameCSVFrameDataReader.getFileName();
      final ArrayList<DigitalFrame> digitalFrames = digitalFrameCSVFrameDataReader.readFrames();
      System.out.println(digitalFramesFileName + ":");
      System.out.printf("%-5s %-20s %-8s %-2s %-2s %-10s %-6s%n", "id", "model", "price", "width",
          "height", "resolution", "memory");
      for (Object frame : digitalFrames.toArray()) {
        objectOutputStream.writeObject(frame);
        System.out.println(frame.toString());
      }
      objectOutputStream.close();

      Journal.log(digitalFramesFileName + " data serialized to frames.dat");

      ObjectInputStream objectInputStream = new ObjectInputStream(
          new FileInputStream("frames.dat"));

      System.out.println("Parsing from frames.dat");
      Object item;
      int counter = 0;
      while (true) {
        try {
          item = objectInputStream.readObject();
          counter++;
          
          System.out.println(item.toString());
        } catch (EOFException e) {
          Journal.log(counter + " frames were serialized from frames.dat");
          break;
        }
      }
    } catch (Exception e) {
      final String message = "Runtime error: " + e.getMessage() + " " + e.getCause();
      System.out.println(message);
      Journal.log(message);
    }
  }
}
