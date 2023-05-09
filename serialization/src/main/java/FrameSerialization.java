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

      ArrayList<Frame> frames = new ArrayList<>();
      frames.addAll(defaultFrameCSVFrameDataReader.readFrames());
      frames.addAll(digitalFrameCSVFrameDataReader.readFrames());

      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
          new FileOutputStream("frames.dat"));
      for (Frame frame : frames) {
        objectOutputStream.writeObject(frame);
      }
    } catch (Exception e) {
      final String message = "Runtime error: " + e.getMessage() + " " + e.getCause();
      System.out.println(message);
      Journal.log(message);
    }
  }
}
