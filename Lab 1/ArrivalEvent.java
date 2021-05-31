/**
 * This class encapsulates an arrival event in the shop
 * simulation.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class ArrivalEvent extends Event {
  /**
   * Customer class associated with this arrival event.
   */
  private Customer customer;

  /**
   * An array to store the counters asscoiated
   * with this arrival event.
   */
  private Counter[] counter;

  /**
   * Constructor for an arrival event.
   *
   * @param customer The customer asscoiated with
   *                 this arrival event.
   * @param counter The array of counters asscoiated
   *                with this event.
   */
  public ArrivalEvent(Customer customer, Counter[] counter) {
    super(customer.getArrival());
    this.customer = customer;
    this.counter = counter;
  }

  /**
   * Returns the string representation of the
   * arrival event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": Customer %d arrives",
        this.customer.getId());
  }

  /**
   * The Logic that the simulation should follow when
   * simulating this arrival event.
   */
  @Override
  public Event[] simulate() {
    // Find the first avaiable conter.
    for (Counter c : this.counter) {
      if (c.isAvailable()) {
        // The customer should go to the first
        // Avaiable counter and get served.
        return new Event[] {
          new ServiceBeginEvent(this.customer, c)
        };
      }
    }
    // If no such counter can be found, the customer should depart.
    return new Event[] {
      new DepartureEvent(this.customer, (this.customer.getArrival()))
    };
  }
}
