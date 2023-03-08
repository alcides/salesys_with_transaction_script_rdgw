package pt.ul.fc.css.example.demo.dataaccess;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceWrapper implements Closeable {

  /** A connection to the database */
  Connection connection;

  public DataSourceWrapper() throws PersistenceException {
    try {
      this.connection = DriverManager.getConnection("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1", "sa", "sa");
    } catch (SQLException e) {
      throw new PersistenceException("Could not obtain database connection!");
    }

    try (Statement s = this.connection.createStatement()) {
      s.executeQuery("SELECT * FROM PRODUCT");
    } catch (SQLException e) {
      try {
        RunSQLScript.runScript(connection, "schema.sql");
        RunSQLScript.runScript(connection, "data.sql");
      } catch (IOException | SQLException e2) {
        e2.printStackTrace();
        throw new PersistenceException("Could not create database schema!");
      }
    }
  }

  /**
   * @return The current database connection
   */
  public Connection getConnection() {
    return connection;
  }

  /** Close the database connection */
  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      // nothing that we can do about it...
    }
  }

  // 2. Prepare statements

  /**
   * Prepare an SQL statement from an SQL string
   *
   * @param sql The SQL text to prepare the command
   * @return The prepared statement for the SQL text
   * @throws PersistenceException In case the prepare statement encounters an error.
   */
  public PreparedStatement prepare(String sql) throws PersistenceException {
    try {
      return connection.prepareStatement(sql);
    } catch (SQLException e) {
      throw new PersistenceException("Error preparing comment", e);
    }
  }

  /**
   * Prepare an SQL statement from an SQL string and informs the underlying JDBC layer to get the
   * automatically generated database keys.
   *
   * @param sql The SQL text to prepare the command
   * @return The prepared statement for the SQL text
   * @throws SQLException PersistenceException In case the prepare statement encounters an error.
   */
  public PreparedStatement prepareGetGenKey(String sql) throws SQLException {
    return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
  }

  // 3. Transactions

  /**
   * Begins a database transaction
   *
   * @throws PersistenceException In case the set commit flag cannot be set
   */
  public void beginTransaction() throws PersistenceException {
    try {
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      throw new PersistenceException("Error starting DB transaction", e);
    }
  }

  /**
   * Commits a transaction
   *
   * @throws PersistenceException In case the commit transaction fails
   */
  public void commit() throws PersistenceException {
    try {
      connection.commit();
    } catch (SQLException e) {
      throw new PersistenceException("Error on commit", e);
    }
    startAutoCommit();
  }

  /**
   * Rolls back a transaction
   *
   * @throws PersistenceException In case the rollback transaction fails
   */
  public void rollback() throws PersistenceException {
    try {
      connection.rollback();
    } catch (SQLException e) {
      throw new PersistenceException("Error on rollback!", e);
    }
    startAutoCommit();
  }

  /**
   * Disables commitment control
   *
   * @throws PersistenceException In case the set commit flag cannot be set
   */
  private void startAutoCommit() throws PersistenceException {
    try {
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      throw new PersistenceException("Error starting auto commit", e);
    }
  }
}
