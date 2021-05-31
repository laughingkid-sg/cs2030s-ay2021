/**
 * This class encapsulates an service end event in the shop
 * simulation.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class ServiceEndEvent extends Event {

  private Customer customer;
  private Counter counter;
  private double time;

  /**
   * Constructor for a service end event.
   *
   * @param customer The customer asscoiated with
   *                 service end event.
   * @param counter The counter that the customer
   *                is associated with this event.
   * @param time The time the customer's service is
   *             ending.
   */
  public ServiceEndEvent(Customer customer, Counter counter, double time) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.time = time;
  }

  @Override
  public String toString() {
    return super.toString() +
      String.format(": Customer %d service done (by Counter %d)",
      this.customer.getId(), this.counter.getId());
  }

  @Override
  public Event[] simulate() {
    this.counter.setAvailability(true);
    return new Event[] {
      new DepartureEvent(this.customer, this.time)
    };
  }
}
