/**
 * This class encapsulates a counter.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class Counter {
  private int id;
  private boolean isAvailable;

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

  public Counter(int id, boolean isAvailable) {
    this.id = id;
    this.isAvailable = isAvailable;
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
}
