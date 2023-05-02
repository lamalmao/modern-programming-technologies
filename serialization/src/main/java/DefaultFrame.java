public class DefaultFrame extends Frame {

  public String material;
  public String shape;

  DefaultFrame() {
    super();

    material = "";
    shape = "";
  }

  @Override
  public void parseCSVLine(String[] data, String[] titles) {
    for (int i = 0; i < titles.length; i++) {
      final String field = titles[i];
      final String value = data[i];
      switch (field) {
        case "id" -> this.id = Integer.parseInt(value);
        case "model" -> this.model = value;
        case "price" -> this.price = Float.parseFloat(value);
        case "width" -> this.width = Integer.parseInt(value);
        case "height" -> this.height = Integer.parseInt(value);
        case "material" -> this.material = value;
        case "shape" -> this.shape = value;
      }
    }
  }

  @Override
  public String toString() {
    return String.format("%-5d %-20s %-8.2f %-2d %-2d %-10s %-10s", this.id,
        this.model, this.price, this.width, this.height, this.material, this.shape);
  }

  @Override
  public DefaultFrame create() {
    return new DefaultFrame();
  }
}
