import domain.LibraryUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.LibraryRepository;
import service.LibraryUserService;

public class Main {
  private static int id = 1;
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);
  public static void main(String... args) throws InterruptedException {
    try {
      saveUser("aaron.harpt", "12345");
    } catch (Exception e) {
      throw new RuntimeException("Could not save user ", e);
    }
    Thread.sleep(2000);

    try {
      LibraryUser user = getUserByUsername("aaron.harpt");
      System.out.println("Credentials: " + user.username() + ", " + user.password());
    } catch (Exception e) {
      throw new RuntimeException("Could not find user ", e);
    }
  }

  public static void saveUser(String username, String password) {
    LOG.info("About to save user {}", username);
    LibraryRepository repository = LibraryRepository.openLibraryRepository("./users.db");
    LibraryUserService service = new LibraryUserService(repository);
    LibraryUser user = new LibraryUser(String.valueOf(id), username, password);
    service.storeLibraryUser(user);

    LOG.info("User {}", username + " has been saved to the database");
    id++;
  }


  public static LibraryUser getUserByUsername(String username) {
    LOG.info("Attempting to retrieve user {}", username);
    LibraryRepository repository = LibraryRepository.openLibraryRepository("./users.db");
    LibraryUserService service = new LibraryUserService(repository);

    return service.getUserByUsername(username);
  }
}
