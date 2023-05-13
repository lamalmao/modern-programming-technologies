import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendors")
public class Vendor extends CSVReadable<Vendor> {

  @Id
  @Column(name = "id")
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "title")
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "address")
  private String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setFrames(List<Frame> frames) {
    this.frames = frames;
  }

  @OneToMany(mappedBy = "vendor")
  private List<Frame> frames;

  public void setStorages(List<Storage> storages) {
    this.storages = storages;
  }

  @OneToMany(mappedBy = "vendor")
  private List<Storage> storages;

  public List<Frame> getFrames() {
    return frames;
  }

  public List<Storage> getStorages() {
    return storages;
  }

  @Override
  public void parseCSVLine(String[] data, String[] titles) {
    for (int i = 0; i < titles.length; i++) {
      final String field = titles[i];
      final String value = data[i];
      switch (field) {
        case "id" -> this.id = Integer.parseInt(value);
        case "title" -> this.title = value;
        case "address" -> this.address = value;
      }
    }
  }

  public Vendor() {
    id = 0;
    title = "";
    address = "";
  }

  public Vendor(int id) {
    this.id = id;
    title = "";
    address = "";
  }

  @Override
  public Vendor create() {
    return new Vendor();
  }

  @Override
  public String toString() {
    return String.format("%3d%15s%20s", id, title, address);
  }
}
