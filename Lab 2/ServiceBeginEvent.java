/**
 * This class encapslates an service begin event in the shop
 * simulation.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class ServiceBeginEvent extends Event {
  private Customer customer;
  private Counter counter;
  private Shop shop;

  /**
   * Constructor for a service begin event.
   *
   * @param customer The customer associated with
   *                 this service begin event.
   * @param counter The counter that will be servicing
   *                this customer.
   * @param shop The shop that the customer is visiting.
   */
  public ServiceBeginEvent(Customer customer, Counter counter, Shop shop) {
    super(customer.getBegin());
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString()
      + String.format(" service begin (by S%d)",
        this.counter.getId());
  }

  @Override
  public Event[] simulate() {
    this.counter.setAvailability(false);
    return new Event[] {
      new ServiceEndEvent(this.customer, this.counter, this.shop)
    };
  }
}
