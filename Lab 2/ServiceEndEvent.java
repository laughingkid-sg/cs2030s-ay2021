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
  private Shop shop;

  /**
   * Constructor for a service end event.
   *
   * @param customer The customer asscoiated with
   *                 service end event.
   * @param counter The counter that the customer
   *                is associated with this event.
   * @param shop The shop that the customer is
   *             visiting.
   */
  public ServiceEndEvent(Customer customer, Counter counter, Shop shop) {
    super(customer.getBegin() + customer.getService());
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public String toString() {
    return super.toString() +
      String.format(": " + customer.toString() + " service done (by S%d)", this.counter.getId());
  }

  @Override
  public Event[] simulate() {

    double time = this.customer.getBegin() + this.customer.getService();
    this.customer.updateDepartureTime(time);

    this.counter.setAvailability(true);
    if (!this.shop.getQueue().isEmpty()) {
      // Get the next customer in queue and update their
      // service begin time.
      Customer c = (Customer) this.shop.getQueue().deq();
      c.updateServiceBeginTime(time);

      return new Event[] {
        new DepartureEvent(this.customer),
        new ServiceBeginEvent(c, this.counter, this.shop)
      };

    } else {
      return new Event[] {
        new DepartureEvent(this.customer)
      };
    }
  }
}
