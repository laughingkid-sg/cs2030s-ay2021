/*
 * This class encapsulates a Shop.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */

public class Shop {

  //private Counter[] counters;
  private Array<Counter> counters;
  private Queue queue;

  /**
   * Constructor used to create a shop that consists
   * of an array of counters.
   *
   * @param int The number of counters to be initalised.
   * @param qLength The queue length of this shop.
   */
  public Shop(int numOfCounters, int qLength, int qCounterLength) {

    //Counter[] counters = new Counter[numOfCounters];
    Array<Counter> counters = new Array<Counter>(numOfCounters);

    for (int i = 0; i < numOfCounters; i++) {
      // counters[i] = new Counter(i, true);
      counters.set(i, new Counter(i, true, qCounterLength));
    }
    this.counters = counters;
    this.queue = new Queue(qLength);
    this.queue.changeQueueType();
  }

  public Counter getAvailableCounter() {

    // Find the first available counter.
    /*
    for (Counter c : this.counters.getArray()) {
      if (c.isAvailable()) {
        // The customer should go to the first.
        // Available counter and get served.
        return c;
      }
    }
    // reutrn null
    */
    /*Counter c = this.counters.min();
    if (c.isQueueFull()){
     System.out.println(c.getQueue().length());
      return null;
    } else {
      return c;
    }*/
    return this.counters.min();
  }

  public Queue getQueue() {
    return this.queue;
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }
}
