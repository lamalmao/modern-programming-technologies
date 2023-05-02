import java.io.Serializable;

interface IFrameReader<T> {

  void parseCSVLine(String[] data, String[] titles);
  T create();
}

public abstract class Frame implements Serializable, IFrameReader<Frame> {

  public int id;
  public int height;
  public int width;
  public float price;
  public String model;

  Frame(int id, String model, float price, int height, int width) {
    this.id = id;
    this.height = height;
    this.width = width;
    this.model = model;
    this.price = price;
  }

  Frame() {
    id = 0;
    height = 0;
    width = 0;
    model = "";
    price = 0;
  }
}