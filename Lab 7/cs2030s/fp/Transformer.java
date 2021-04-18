package cs2030s.fp;

/**
 * Represent a function that transforms one value into another, possible of different types.
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @param <U> The type of the input value
 * @param <T> The type of the result value
 */
@FunctionalInterface
public interface Transformer<U, T> {
  /**
   * The function method to transform the value u.
   *
   * @param u The input value
   * @return The value after applying the given transformation on u.
   */
  T transform(U u);
}
