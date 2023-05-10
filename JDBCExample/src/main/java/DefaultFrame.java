import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

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

  @Override
  public PreparedStatement getValuesStatement(String table, Connection connection)
      throws SQLException {
    PreparedStatement statement = connection.prepareStatement(
        "INSERT INTO " + table
            + " (id, model, price, width, height, material, shape) VALUES (?, ?, ?, ?, ?, ?, ?)");

    statement.setInt(1, id);
    statement.setString(2, model);
    statement.setFloat(3, price);
    statement.setInt(4, width);
    statement.setInt(5, height);
    statement.setString(6, material);
    statement.setString(7, shape);

    return statement;
  }

  @Override
  public String getCreateTableUpdate(String name) {
    return String.format(
        "CREATE TABLE %s (id int PRIMARY KEY, model varchar(255), price float8, width int, height int, material varchar(255), shape varchar(255))",
        name);
  }

  @Override
  public String getValuesLine() {
    return String.format(Locale.US, "(%d, '%s', %.4f, %d, %d, '%s', '%s')", id, model, price, width, height,
        material, shape);
  }

  @Override
  public String getValuesHeaderLine() {
    return "(id, model, price, width, height, material, shape)";
  }
}
