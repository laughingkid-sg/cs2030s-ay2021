import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

/**
 * A BusSg class encapsulate the data related to the bus services and
 * bus stops in Singapore, and supports queries to the data.
 *
 * @author: Ooi Wei Tsang 
 * @version: CS2030S AY20/21 Semester 2, Lab 8
 */
class BusSg {

  /**
   * Given a bus stop and a name, find the bus services that serve between
   * the given stop and any bus stop with matching mame.
   *
   * @param  stop The bus stop.  Assume to be not null.
   * @param  searchString The (partial) name of other bus stops, assume not null.
   * @return The (optional) bus routes between the stops encapsulated in a
   *          CompletableFuture.
   */
  public static CompletableFuture<BusRoutes> findBusServicesBetween(BusStop stop,
      String searchString) {
    try {
      CompletableFuture<Map<BusService, CompletableFuture<Set<BusStop>>>> validServices =
          stop.getBusServices().thenApply(validSrv -> {
            return validSrv.stream().collect(Collectors.toMap(
                service -> service,
                service -> service.findStopsWith(searchString)));
          });
      return validServices.thenApply(vs -> new BusRoutes(stop, searchString, vs));
    } catch (CompletionException e) {
      System.err.println("Unable to complete query: " + e);
      return CompletableFuture.completedFuture(new BusRoutes(stop, searchString, Map.of()));
    }
  }
}
