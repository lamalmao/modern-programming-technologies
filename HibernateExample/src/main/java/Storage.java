import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "storages")
public class Storage extends CSVReadable<Storage> {

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Id
  @Column(name = "id", nullable = false)
  private int id;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "address")
  private String address;

  public Vendor getVendor() {
    return vendor;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }

  @JoinColumn(name = "vendor")
  @ManyToOne(fetch = FetchType.LAZY)
  private Vendor vendor;

  public void setFrames(List<Frame> frames) {
    this.frames = frames;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "storage")
  private List<Frame> frames;

  public List<Frame> getFrames() {
    return frames;
  }

  public Storage() {
    id = 0;
    address = "";
    vendor = new Vendor();
  }

  public Storage(int id) {
    this.id = id;
    address = "";
    vendor = new Vendor();
  }

  @Override
  void parseCSVLine(String[] data, String[] titles) {
    for (int i = 0; i < titles.length; i++) {
      final String field = titles[i];
      final String value = data[i];
      switch (field) {
        case "id" -> id = Integer.parseInt(value);
        case "address" -> address = value;
        case "vendor" -> vendor = new Vendor(Integer.parseInt(value));
      }
    }
  }

  @Override
  Storage create() {
    return new Storage();
  }

  @Override
  public String toString() {
    return String.format("%3s%30s", id, address);
  }
}
