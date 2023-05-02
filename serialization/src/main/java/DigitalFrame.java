public class DigitalFrame extends Frame {

  public String resolution;
  public int memory;

  DigitalFrame() {
    super();

    this.resolution = "";
    this.memory = 0;
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
        case "resolution" -> this.resolution = value;
        case "memory" -> this.memory = Integer.parseInt(value);
      }
    }
  }

  @Override
  public String toString() {
    return String.format("%-5d %-20s %-8.2f %-2d %-2d %-10s %-5d", this.id,
        this.model, this.price, this.width, this.height, this.resolution, this.memory);
  }

  @Override
  public DigitalFrame create() {
    return new DigitalFrame();
  }
}
