package repository;

import domain.LibraryUser;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;

class LibraryJdbcRepository implements LibraryRepository {
  private static final String H2_DATABASE_URL =
    "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";

  private static final String INSERT_USER = """
    MERGE INTO LIBRARYUSER (id, username, password)
      VALUES (?, ?, ?)
    """;

  private final DataSource dataSource;

  public LibraryJdbcRepository(String databaseFile) {
    JdbcDataSource jdbcDataSource = new JdbcDataSource();
    jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));
    this.dataSource = jdbcDataSource;
  }

  @Override
  public void saveUser(LibraryUser user) {
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement statement = connection.prepareStatement(INSERT_USER);
      statement.setString(1, user.id());
      statement.setString(2, user.username());
      statement.setString(3, user.password());
      statement.execute();
    } catch (SQLException e) {
      throw new RepositoryException("Failed to save " + user, e);
    }
  }

  @Override
  public LibraryUser getUserByUsername(String username) {
    try (Connection connection = dataSource.getConnection()) {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM LIBRARYUSER WHERE USERNAME = '" + username + "'");

      LibraryUser user = null;
      while (resultSet.next()) {
        user = new LibraryUser(
          resultSet.getString("id"),
          resultSet.getString("username"),
          resultSet.getString("password")
        );
      }

      return user;
    } catch (SQLException e) {
      throw new RepositoryException("Failed to retrieve user " + username, e);
    }
  }
}
