package cs2030s.fp;

/**
 * Represent a function that consumes a value.
 * CS2030S PE2
 * AY20/21 Semester 2
 *
 * @param <T> The type of the value produced.
 */
@FunctionalInterface
public interface Consumer<T> {
  void consume(T t);
}
