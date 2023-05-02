import java.io.Serializable;

public abstract class Frame implements Serializable {

  public int height;
  public int width;
  public float price;
  public String model;

  Frame(String model, float price, int height, int width) {
    this.height = height;
    this.width = width;
    this.model = model;
    this.price = price;
  }
}