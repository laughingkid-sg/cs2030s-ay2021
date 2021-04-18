import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Encapsulate a bus stop with a unique String id, the location (in long, lat),
 * and human friendly name.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030S AY20/21 Semester 2, Lab 8
 */
class BusStop {
  /** A unique String id. */
  private final String stopId; // unique

  /** A human friendly name. */
  private final String name;
  
  /**
   * Constructor for this bus stop.
   * @param   id     The id of this bus stop.
   * @param   name   A human friendly name of the bus stop.
   */
  public BusStop(String id, String name) {
    this.stopId = id;
    this.name = name;
  }

  /**
   * Constructor for this bus stop without name.
   * @param   id     The id of this bus stop.
   */
  public BusStop(String id) {
    this.stopId = id;
    this.name = "";
  }

  /**
   * Checks if the bus stop name matches the given string.
   * @param  name The string to match.
   * @return true if the name matches; false otherwise.
   */
  public boolean matchName(String name) {
    return this.name.toUpperCase().indexOf(name.toUpperCase()) != -1;
  }

  /**
   * Return the set of bus services that serve this bus stop as
   * a set.  Query the web server.
   * @return A set of BusService that serve this bus stop.
   */
  public Set<BusService> getBusServices() {
    // Thread.sleep(200);
    // bus services that visit this stop

    Scanner sc = new Scanner(BusAPI.getBusServicesAt(stopId));
    Set<BusService> busServices = sc
        .useDelimiter("\n")
        .tokens()
        .skip(1) // skip first line
        .flatMap(line -> Stream.of(line.split(",")))
        .map(id -> new BusService(id))
        .collect(Collectors.toSet());
    sc.close();
    return busServices;
  }

  /**
   * Checks of this bus stop equals to another bus stop -- two bus
   * stops are equal if their id is the same.
   * @param  o Another object to compare against.
   * @return  true if the two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof BusStop)) {
      return false;
    }
    return ((BusStop) o).stopId.equals(this.stopId);
  }

  /**
   * Return a hash code of the bus stop.
   * @return The hash code of this bus stop.
   */
  @Override
  public int hashCode() {
    return stopId.hashCode();
  }

  /**
   * Return a string representation of the bus stop.
   * @return Return the name of the bus stop.
   */
  @Override
  public String toString() {
    return stopId + " " + name;
  }
}
