package cs2030s.fp;

/**
 * Represent a function that produce a value.
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @param <T> The type of the value produced.
 */
@FunctionalInterface
public interface Producer<T> {
  /**
   * The functional method to produce a value.
   *
   * @return The value produced.
   */
  T produce();
}
