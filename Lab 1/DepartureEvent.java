/**
 * This class encapsulates an departure event in the
 * shop simulation.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class DepartureEvent extends Event {

  private Customer customer;
  private double time;

  /**
   * Constructor for a departure event.
   *
   * @param customer The customer ascociated with
   *                 this departure event.
   *
   * @param time The departure time of a customer,
   *             calculated by adding the arrival time
   *             and the service time.
   */
  public DepartureEvent(Customer customer, double time) {
    super(time);
    this.customer = customer;
    this.time = time;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": Customer %d departed",
        this.customer.getId());
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
