package repository;

import domain.LibraryUser;

public interface LibraryRepository {
  void saveUser(LibraryUser user);
  LibraryUser getUserByUsername(String username);
  static LibraryRepository openLibraryRepository(String databaseFile) {
    return new LibraryJdbcRepository(databaseFile);
  }
}
