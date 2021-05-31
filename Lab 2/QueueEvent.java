/**
 * This class encapsulates and queue event in the shop
 * simulation.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class QueueEvent extends Event {
  /**
   * Customer class associated with this queue event.
   */
  private Customer customer;

  /**
   * A shop class to store the counters asscoiated
   * with this queue event.
   */
  private Shop shop;

  /**
   * Constructor for an queue event.
   *
   * @param customer The customer asscoiated with
   *                 this queue event.
   * @param shop The shop asscoiated
   *                with this event.
   */
  public QueueEvent(Customer customer, Shop shop) {
    super(customer.getArrival());
    this.customer = customer;
    this.shop = shop;
  }

  /**
   * Returns the string representation of the
   * queue event.
   *
   * @return A string representing the event.
   */

  @Override
  public String toString() {
    return super.toString() + ": " +  customer.toString() + " joined queue " +
      this.shop.getQueue().toString();
  }

  /**
   * The logic that the simulation should follow when
   * simulating this queue event.
   */
  @Override
  public Event[] simulate() {
    this.shop.getQueue().enq(this.customer);
    return new Event[] {};
  }
}
