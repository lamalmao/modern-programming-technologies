import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DatabaseWorker {

  protected Connection connection;


  private String[] loadCredentials(String filename) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String[] result = {reader.readLine(), reader.readLine(), reader.readLine()};
    reader.close();
    return result;
  }

  public DatabaseWorker() {
    try {
      Properties properties = new Properties();

      String[] credentials = loadCredentials("credentials.txt");
      final String url = credentials[0];
      properties.setProperty("user", credentials[1]);
      properties.setProperty("password", credentials[2]);

      connection = DriverManager.getConnection(url, properties);
    } catch (IOException e) {
      System.out.println("Ошибка во время загрузки данных авторизации");
      System.err.println(e.getMessage());
      System.exit(-1);
    } catch (SQLException e) {
      System.out.println("Ошибка во время подключения к базе данных");
      System.err.println(Arrays.toString(e.getStackTrace()));
      System.exit(-1);
    }
  }

  private boolean tableExists(String name) throws SQLException {
    name = name.toLowerCase();

    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet resultSet = metaData.getTables(null, null, name, null);
    boolean found = false;

    while (resultSet.next()) {
      String tableName = resultSet.getString("TABLE_NAME");
      if (tableName != null && tableName.equals(name)) {
        found = true;
        break;
      }
    }

    return found;
  }

  public void createTableFromObject(String name, Loadable object, Boolean dropIfExists)
      throws SQLException {
    if (tableExists(name)) {
      if (dropIfExists) {
        Statement dropStatement = connection.createStatement();
        dropStatement.executeUpdate("DROP TABLE " + name.toLowerCase());
      } else {
        return;
      }
    }

    Statement statement = connection.createStatement();
    statement.executeUpdate(object.getCreateTableUpdate(name));
  }

  public void loadObjectToBase(Loadable object, String table) throws SQLException {
    PreparedStatement statement = object.getValuesStatement(table, connection);
    statement.executeUpdate();
  }

  public void loadObjectsToBase(List<? extends Loadable> objects, String table)
      throws SQLException {
    Statement statement = connection.createStatement();
    statement.executeUpdate(Loadable.getLoadObjectsUpdate(objects, table));
  }

  public Frame getById(int id, int type) throws SQLException {
    PreparedStatement search = connection.prepareStatement(
        "SELECT * FROM "
            .concat(type == 1 ? "digital_frames" : "default_frames")
            .concat(" WHERE id = ?")
    );
    search.setInt(1, id);
    ResultSet result = search.executeQuery();
    if (!result.next()) {
      throw new RuntimeException("Object not found");
    }

    if (type == 1) {
      return new DigitalFrame(result);
    } else {
      return new DefaultFrame(result);
    }
  }

  public ArrayList<Frame> getAllObjects(String table, int type) throws Exception, SQLException {
    Statement search = connection.createStatement();
    ResultSet result = search.executeQuery("SELECT * FROM ".concat(table));
    if (!result.next()) {
      throw new Exception("Nothing found");
    }

    ArrayList<Frame> objects = new ArrayList<>();
    while (result.next()) {
      if (type == 1) {
        objects.add(new DigitalFrame(result));
      } else {
        objects.add(new DefaultFrame(result));
      }
    }

    return objects;
  }
}
