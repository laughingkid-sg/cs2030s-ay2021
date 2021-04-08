package cs2030s.fp;

/**
 * Represent a conditional statement that returns either true of false.
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @param <T> The type of the variable to be tested with this conditional statement.
 */
@FunctionalInterface
public interface BooleanCondition<T> {
  /**
   * The functional method to test if the condition is true/false on the given value t.
   *
   * @param t The variable to test
   * @return The return value of the test.
   */
  boolean test(T t);
}
