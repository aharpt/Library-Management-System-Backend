package domain;

public record LibraryUser(String id, String username, String password) {
  public LibraryUser {
    filled(id);
    filled(username);
    filled(password);
  }


  private static void filled(String arg) {
    if (arg == null || arg.isBlank()) {
      throw new IllegalArgumentException("No value present!");
    }
  }
}
