/**
 * This class encapsulates a counter.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class Counter implements Comparable<Counter> {
  private int id;
  private boolean isAvailable;
  private Queue queue;

  /**
   * Constructor for a counter.
   *
   * @param id The id of the counter associated with
   *           this class.
   *
   * @param isAvailable The indicator if this
   *                    counter is available.
   *
  */

  public Counter(int id, boolean isAvailable, int qLength) {
    this.id = id;
    this.isAvailable = isAvailable;
    this.queue = new Queue(qLength);
  }

  public int getId() {
    return this.id;
  }

  public boolean isAvailable() {
    return this.isAvailable;
  }

  public void setAvailability(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public int getQueueLength() {
    return this.queue.length();
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  public boolean isEmpty() {
    return this.queue.isEmpty();
  }

  public Queue getQueue() {
    return this.queue;
  }

  @Override
  public int compareTo(Counter c) {
    if (c.isAvailable()) {
      return 1;
    } else if (this.isAvailable) {
      return -1;
    } else {
      return Integer.compare(this.queue.length(), c.getQueueLength());
    }
  }
}
