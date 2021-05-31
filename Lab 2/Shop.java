/*
 * This class encapsulates a Shop.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */

public class Shop {

  private Counter[] counters;
  private Queue queue;

  /**
   * Constructor used to create a shop that consists
   * of an array of counters.
   *
   * @param int The number of counters to be initalised.
   * @param qLength The queue length of this shop.
   */
  public Shop(int numOfCounters, int qLength) {

    Counter[] counters = new Counter[numOfCounters];

    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter(i, true);
    }

    this.counters = counters;
    this.queue = new Queue(qLength);
  }

  public Counter getAvailableCounter() {
    // Find the first available counter.
    for (Counter c : this.counters) {
      if (c.isAvailable()) {
        // The customer should go to the first.
        // Available counter and get served.
        return c;
      }
    }
    return null;
  }

  public Queue getQueue() {
    return this.queue;
  }
}
