import java.util.List;

/**
 * Test 4 for CS2030S Mock PE 2 (AY20/21 Sem 2).  Tests
 * for Trace flatMap method
 *
 * @author Ooi Wei Tsang
 */
class Test4 {

  /**
   * Recursive method to keep dividing a long value by 2 until it
   * reaches 1.
   *
   * @param n The long value to divide
   * @return A trace of the sequence of values from n to 2 (or 1)
   */
  static Trace<Long> div2(Long n) {
    return (n <= 2) ? Trace.of(1L) : Trace.of(n / 2).flatMap(y -> div2(y));
  }

  /**
   * Main method for Test4.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    i.expectReturn("Trace.of(1).flatMap(x -> Trace.of(x + 1).map(y -> y + 1)).get()",
        () -> Trace.of(1).flatMap(x -> Trace.of(x + 1).map(y -> y + 1)).get(), 
        3);

    i.expectReturn("Trace.of(1).flatMap(x -> Trace.of(x + 1).map(y -> y + 1)).history()",
        () -> Trace.of(1).flatMap(x -> Trace.of(x + 1).map(y -> y + 1)).history(), 
        List.of(1, 2));

    i.expectReturn("Trace.of(4905L).flatMap(x -> div2(x)).history()",
        () -> Trace.of(4905L).flatMap(x -> div2(x)).history(), 
        List.of(4905L, 2452L, 1226L, 613L, 306L, 153L, 76L, 38L, 19L, 9L, 4L, 2L));

    i.expect("Trace<T> is immutable and Trace::flatMap does not mutate the instance",
        () -> {
          Trace<Boolean> t = Trace.of(true, true, false, true, false);
          t.flatMap(x -> Trace.of(!x));
          return t.get() == true && t.history().equals(List.of(true, false, true, false));
        }, true);
  }
}
