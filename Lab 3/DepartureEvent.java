/**
 * This class encapsulates an departure event in the
 * shop simulation.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class DepartureEvent extends Event {

  private Customer customer;

  /**
   * Constructor for a departure event.
   *
   * @param customer The customer ascociated with
   *                 this departure event.
   *
   */
  public DepartureEvent(Customer customer) {
    super(customer.getDeparture());
    this.customer = customer;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " departed";
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
