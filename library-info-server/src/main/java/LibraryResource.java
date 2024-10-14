import domain.LibraryUser;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.LibraryRepository;
import repository.RepositoryException;

@Path("/library")
public class LibraryResource {
  private static final Logger LOG = LoggerFactory.getLogger(LibraryResource.class);

  private final LibraryRepository libraryRepository;

  public LibraryResource(LibraryRepository repository) {
    this.libraryRepository = repository;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/users")
  public LibraryUser getUserByUsername(@QueryParam("username")String username) {
    try {
      return libraryRepository.getUserByUsername(username);
    } catch (RepositoryException e) {
        LOG.error("Could not retrieve user from the database", e);
        throw new RuntimeException(e.getMessage());
    }
  }

  @POST
  @Path("/users/saveUser")
  @Consumes(MediaType.APPLICATION_JSON)
  public void saveUser(LibraryUser user) {
    if (user == null) {
      LOG.error("Cannot save a null user");
      return;
    }

    try {
      libraryRepository.saveUser(user);
    } catch (RepositoryException e) {
      LOG.error("Caught repository exception: ", e);
      throw new RuntimeException(e.getMessage());
    }
  }
}
