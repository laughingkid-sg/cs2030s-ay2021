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

  /**
   * Constructor for a service begin event.
   *
   * @param customer The customer associated with
   *                 this service begin event.
   * @param counter The counter that will be servicing
   *                this customer.
   */
  public ServiceBeginEvent(Customer customer, Counter counter) {
    super(customer.getArrival());
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString() +
      String.format(": Customer %d service begin (by Counter %d)",
      this.customer.getId(), this.counter.getId());
  }

  @Override
  public Event[] simulate() {
    this.counter.setAvailability(false);
    double endTime = this.customer.getArrival() +
         this.customer.getService();
    return new Event[] {
      new ServiceEndEvent(this.customer, this.counter, endTime)
    };
  }
}
