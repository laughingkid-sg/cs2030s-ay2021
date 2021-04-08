import java.util.List;

/**
 * A dummy subclass for Trace for Testing
 * CS2030S Mock PE 2
 * AY20/21 Semester 2
 *
 * @param <T> The type of the value encapsulated in Trace
 */
class SubTrace<T> extends Trace<T> { 
  /**
   * Constructor for SubTrace to invoke the constructor for Trace.
   * For testing purpose only.  It does not support passing in 
   * additional history.
   * 
   * @param v The value to encapsulate in the trace
   */
  private SubTrace(T v) {
    super(v, List.of());
  }

  /**
   * The factory method for SubTrace.
   * It does not support passing in additional history.
   * 
   * @param <T> The type of the value to encapsulate in a trace.
   * @param v The value to encapsulate in the trace
   */
  static <T> SubTrace<T> of(T v) {
    return new SubTrace<T>(v);
  }
}
