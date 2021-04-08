import java.util.List;

/**
 * Test 1 for CS2030S Mock PE 2 (AY20/21 Sem 2).  Tests
 * for Trace get() and history() methods
 *
 * @author Ooi Wei Tsang
 */
class Test1 {
  /**
   * Main method for Test1.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    i.expectReturn("Trace.of(\"hello\").get()",
        () -> Trace.of("hello").get(), 
        "hello");
    i.expectReturn("Trace.of(\"hello\").history()",
        () -> Trace.of("hello").history(), 
        List.of());
    i.expectReturn("Trace.of(\"hello\", \"h\", \"he\", \"hel\", \"hell\").get()",
        () -> Trace.of("hello", "h", "he", "hel", "hell").get(), 
        "hello");
    i.expectReturn("Trace.of(\"hello\", \"h\", \"he\", \"hel\", \"hell\").get()",
        () -> Trace.of("hello", "h", "he", "hel", "hell").history(), 
        List.of("h", "he", "hel", "hell"));
    i.expectReturn("Trace.of(1).get()", 
        () -> Trace.of(1).get(), 
        1);
    i.expectReturn("Trace.of(1, 5, 4, 3, 2).history()",
        () -> Trace.of(1, 5, 4, 3, 2).history(), 
        List.of(5, 4, 3, 2));
  }
}
