import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.LibraryRepository;

import java.net.URI;

public class LibraryServer {
  private static final Logger LOG = LoggerFactory.getLogger(LibraryServer.class);
  private static final String BASE_URL = "http://localhost:8080/";
  public static void main(String... args) {
    LOG.info("Starting HTTP Server");
    LibraryRepository repository = LibraryRepository.openLibraryRepository("./users.db");
    ResourceConfig config = new ResourceConfig().register(new LibraryResource(repository));

    GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URL), config);
  }
}
