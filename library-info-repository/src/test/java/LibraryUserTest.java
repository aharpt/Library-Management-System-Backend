import domain.LibraryUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LibraryUserTest {
  @Test
  public void validRecord() {
    LibraryUser user = new LibraryUser("1", "user", "password");
    Assertions.assertEquals("user", user.username());
  }

  @Test
  public void invalidRecord() {
    Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      LibraryUser user = new LibraryUser("1", "user", "");
    });

    Assertions.assertEquals("No value present!", exception.getMessage());
  }
}
