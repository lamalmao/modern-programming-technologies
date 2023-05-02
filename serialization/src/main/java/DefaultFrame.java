public class DefaultFrame extends Frame {
  public String material;
  public String shape;

  DefaultFrame(String model, float price, int width, int height, String material, String shape) {
    super(model, price, height, width);

    this.material = material;
    this.shape = shape;
  }
}
