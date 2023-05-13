import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "frames")
public class Frame extends CSVReadable<Frame> implements Serializable {

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Id
  @Column(name = "id")
  private int id;

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Column(name = "height")
  private int height;

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  @Column(name = "width")
  private int width;

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  @Column(name = "price")
  private float price;

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @Column(name = "model")
  private String model;

  @JoinColumn(name = "vendor")
  @ManyToOne(fetch = FetchType.LAZY)
  private Vendor vendor;

  @JoinColumn(name = "storage")
  @ManyToOne(fetch = FetchType.LAZY)
  private Storage storage;

  public Vendor getVendor() {
    return vendor;
  }

  public Storage getStorage() {
    return storage;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }

  public void setStorage(Storage storage) {
    this.storage = storage;
  }

  Frame() {
    id = 0;
    height = 0;
    width = 0;
    model = "";
    price = 0;
    vendor = new Vendor();
    storage = new Storage();
  }

  @Override
  void parseCSVLine(String[] data, String[] titles) {
    for (int i = 0; i < titles.length; i++) {
      final String field = titles[i];
      final String value = data[i];
      switch (field) {
        case "id" -> id = Integer.parseInt(value);
        case "height" -> height = Integer.parseInt(value);
        case "width" -> width = Integer.parseInt(value);
        case "model" -> model = value;
        case "price" -> price = Float.parseFloat(value);
        case "vendor" -> vendor = new Vendor(Integer.parseInt(value));
        case "storage" -> storage = new Storage(Integer.parseInt(value));
      }
    }
  }

  @Override
  Frame create() {
    return new Frame();
  }

  @Override
  public String toString() {
    return String.format("%7d%4d%4d%20s%10.2f%3d%3d", id, height, width, model, price, vendor.getId(),
        storage.getId());
  }
}