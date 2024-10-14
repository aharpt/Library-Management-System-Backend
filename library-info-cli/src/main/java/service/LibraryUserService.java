package service;

import domain.LibraryUser;
import repository.LibraryRepository;

public class LibraryUserService {
  private final LibraryRepository repository;

  public LibraryUserService(LibraryRepository repository) {
    this.repository = repository;
  }

  public void storeLibraryUser(LibraryUser user) {
    repository.saveUser(user);
  }

  public LibraryUser getUserByUsername(String username) {
    return repository.getUserByUsername(username);
  }
}
