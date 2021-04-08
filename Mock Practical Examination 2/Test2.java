import java.util.List;

/**
 * Test 2 for CS2030S Mock PE 2 (AY20/21 Sem 2).  Tests
 * for Trace back() and equals() methods
 *
 * @author Ooi Wei Tsang
 */
class Test2 {
  /**
   * Main method for Test2.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    i.expectReturn("Trace.of(\"hello\", \"h\", \"he\", \"hel\", \"hell\").back(2).get()",
        () -> Trace.of("hello", "h", "he", "hel", "hell").back(2).get(), "hel");
    i.expectReturn("Trace.of(\"hello\", \"h\", \"he\", \"hel\", \"hell\").back(2).history()",
        () -> Trace.of("hello", "h", "he", "hel", "hell").back(2).history(), List.of("h", "he"));
    i.expectReturn("Trace.of(\"hello\", \"h\", \"he\", \"hel\", \"hell\").back(9).get()",
        () -> Trace.of("hello", "h", "he", "hel", "hell").back(9).get(), "h");
    i.expectReturn("Trace.of(1, 5, 4, 3, 2).equals(Trace.of(1, 2, 3, 4, 5))",
        () -> Trace.of(1, 5, 4, 3, 2).equals(Trace.of(1, 2, 3, 4, 5)), false);
    i.expectReturn("Trace.of(1, 5, 4, 3, 2).equals(Trace.of(1))",
        () -> Trace.of(1, 5, 4, 3, 2).equals(Trace.of(1)), false);
    i.expectReturn("Trace.of(1, 5, 4, 3, 2).equals(Trace.of(0, 5, 4, 3, 2, 1).back(1))",
        () -> Trace.of(1, 5, 4, 3, 2).equals(Trace.of(0, 5, 4, 3, 2, 1).back(1)), true);
    i.expect("Trace<T> is immutable and Trace::back does not mutate the instance",
        () -> {
          Trace<Boolean> t = Trace.of(true, false, true, true, false);
          t.back(1);
          return t.get() == true && t.history().equals(List.of(false, true, true, false));
        }, true);
  }
}
