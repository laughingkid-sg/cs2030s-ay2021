/**
 * This class encapsulates queue event in the shop
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
   * A queue class to store the counters asscoiated
   * with this queue event.
   */
  private Queue queue;

  private Counter counter;
  /**
   * Constructor for an queue event.
   *
   * @param customer The customer asscoiated with
   *                 this queue event.
   * @param queue The queue asscoiated
   *                with this event.
   */

  public QueueEvent(Customer customer, Queue queue, Counter counter) {
    super(customer.getQueue());
    this.customer = customer;
    this.queue = queue;
    this.counter = counter;
  }

  /**
   * Returns the string representation of the
   * queue event.
   *
   * @return A string representing the event.
   */

  @Override
  public String toString() {
    if (!queue.isCounterQueue()) {
      return super.toString() + ": " +  customer.toString() + " joined shop queue " +
        this.queue.toString();
    } else {
      return super.toString() + ": " +  customer.toString() + " joined counter queue (at S" +
        this.counter.getId() + " " +
        this.queue.toString() + ")";
    }
  }

  /**
   * The logic that the simulation should follow when
   * simulating this queue event.
   */
  @Override
  public Event[] simulate() {
    this.queue.enq(this.customer);
    return new Event[] {};
  }
}
