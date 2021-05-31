package cs2030s.fp;

/**
 * Represent a function that runs a task.
 * CS2030S PE2
 * AY20/21 Semester 2
 */
@FunctionalInterface
public interface Runnable {
  /**
   * The function method to run some code.
   *
   * @throws Throwable if something goes wrong.
   */
  void run() throws Throwable;
}
