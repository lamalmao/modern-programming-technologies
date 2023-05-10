import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

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

  @Override
  public PreparedStatement getValuesStatement(String table, Connection connection)
      throws SQLException {
    PreparedStatement statement = connection.prepareStatement(
        "INSERT INTO " + table + " (id, model, price, width, height, resolution, memory) VALUES (?, ?, ?, ?, ?, ?, ?)");

    statement.setInt(1, id);
    statement.setString(2, model);
    statement.setFloat(3, price);
    statement.setInt(4, width);
    statement.setInt(5, height);
    statement.setString(6, resolution);
    statement.setInt(7, memory);

    return statement;
  }

  @Override
  public String getCreateTableUpdate(String name) {
    return String.format(
        "CREATE TABLE %s (id int PRIMARY KEY, model varchar(255), price float8, width int, height int, resolution varchar(255), memory int)",
        name);
  }

  @Override
  public String getValuesLine() {
    return String.format(Locale.US, "(%d, '%s', %.4f, %d, %d, '%s', %d)", id, model, price, width, height, resolution, memory);
  }

  @Override
  public String getValuesHeaderLine() {
    return "(id, model, price, width, height, resolution, memory)";
  }
}
