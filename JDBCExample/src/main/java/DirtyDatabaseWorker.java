import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class DirtyDatabaseWorker extends DatabaseWorker {

  public DirtyDatabaseWorker() throws SQLException {
    super();
    connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
    connection.setAutoCommit(false);
  }

  public void dirtyReadAndRollback(String readQuery) {
    Thread task = new Thread(() -> {
      try {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(readQuery);
        result.next();
        System.out.println(result.getString("model"));

        Savepoint save = connection.setSavepoint("pre delete");
        Thread.sleep(1500);
        connection.rollback(save);
        connection.commit();
        Thread.currentThread().interrupt();
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    });
    task.start();
  }
}
