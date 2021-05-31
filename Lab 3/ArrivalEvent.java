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
   * A shop class to store the counters asscoiated
   * with this arrival event.
   */
  private Shop shop;

  /**
   * Constructor for an arrival event.
   *
   * @param customer The customer asscoiated with
   *                 this arrival event.
   * @param shop The shop asscoiated
   *                with this event.
   */
  public ArrivalEvent(Customer customer, Shop shop) {
    super(customer.getArrival());
    this.customer = customer;
    this.shop = shop;
  }

  /**
   * Returns the string representation of the
   * arrival event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " arrived "
      + this.shop.getQueue().toString();
  }

  /**
   * The logic that the simulation should follow when
   * simulating this arrival event.
   */
  @Override
  public Event[] simulate() {

    // Get the next available counter.
    Counter c = this.shop.getAvailableCounter();

    if (c.isAvailable() || !c.isQueueFull()) {
      // When there is available counter customer can begin
      // service immediately.
      if (c.isAvailable()) {
        this.customer.updateServiceBeginTime(this.customer.getArrival());
        return new Event[] {
          new ServiceBeginEvent(this.customer, c, this.shop)
        };
      } else {
        this.customer.updateQueueTime(this.customer.getArrival());
        return new Event[] {
          new QueueEvent(this.customer, c.getQueue(), c)
        };
      }
    } else if (!this.shop.isQueueFull()) {

      // When no counters are available and there is
      // space in the queue, customer is added to the queue.

      this.customer.updateQueueTime(this.customer.getArrival());
      return new Event[] {
        new QueueEvent(this.customer, this.shop.getQueue(), null)
      };
    } else {

      // Else the customer departs.
      this.customer.updateDepartureTime(this.customer.getArrival());
      return new Event[] {
        new DepartureEvent(this.customer)
      };
    }
  }
}
