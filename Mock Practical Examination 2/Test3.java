import java.util.Arrays;
import java.util.List;

/**
 * Test 3 for CS2030S Mock PE 2 (AY20/21 Sem 2).  Tests
 * for Trace map() 
 *
 * @author Ooi Wei Tsang
 */
class Test3 {
  /**
   * Main method for Test3.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Trace.of(\"h\").map(s -> s + \"ello\").get()",
        () -> Trace.of("h").map(s -> s + "ello").get(), 
        "hello");
    i.expectReturn("Trace.of(\"h\").map(s -> s + \"ello\").history()",
        () -> Trace.of("h").map(s -> s + "ello").history(), 
        List.of("h"));
    i.expectReturn("Trace.of(1, 0).map(x -> x + 1).map(y -> y + 2).history()",
        () -> Trace.of(1, 0).map(x -> x + 1).map(y -> y + 2).history(), 
        List.of(0, 1, 2));
    i.expectReturn("Trace.of(1, 0).map(x -> x + 1).back(1).map(y -> y + 2).history()",
        () -> Trace.of(1, 0).map(x -> x + 1).back(1).map(y -> y + 2).history(), 
        List.of(0, 1));
    i.expectReturn("Trace.of(\"h\").map(x -> x).get().equals(Trace.of(\"h\").get())",
        () -> Trace.of("h").map(x -> x).get().equals(Trace.of("h").get()), 
        true);
    i.expectReturn("Trace.of(\"h\").map(x -> x).equals(Trace.of(\"h\"))",
        () -> Trace.of("h").map(x -> x).equals(Trace.of("h")), 
        false);

    i.expectReturn("Trace.of(10).map(x -> f(x)).map(x -> g(x)).get()" +
        ".equals(Trace.of(10).map(x -> g(f(x))).get())",
        () -> {
          Transformer<Integer, Integer> f = x -> x + 1;
          Transformer<Integer, Integer> g = x -> x * 10;
          Transformer<Integer, Integer> h = x -> g.transform(f.transform(x));
          return Trace.of(10).map(f).map(g).get().equals(Trace.of(10).map(h).get());
        }, true);
    i.expectReturn("Trace.of(10).map(x -> f(x)).map(x -> g(x))" +
        ".equals(Trace.of(10).map(x -> g(f(x))))",
        () -> {
          Transformer<Integer, Integer> f = x -> x + 1;
          Transformer<Integer, Integer> g = x -> x * 10;
          Transformer<Integer, Integer> h = x -> g.transform(f.transform(x));
          return Trace.of(10).map(f).map(g).equals(Trace.of(10).map(h));
        }, false);
    i.expectReturn("Applying collatz to 9 leads to ",
        () -> {
          Transformer<Integer, Integer> collatz = x -> (x % 2 == 0) ? (x / 2) : (3 * x + 1);
          Trace<Integer> t = Trace.of(9);
          while (t.get() != 1) {
            t = t.map(collatz);
          }
          return t.history();
        }, Arrays.asList(9, 28, 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2));
    i.expect("Trace<T> is immutable and Trace::map does not mutate the instance",
        () -> {
          Trace<Boolean> t = Trace.of(true, false, true, true, false);
          t.map(x -> !x);
          return t.get() == true && t.history().equals(List.of(false, true, true, false));
        }, true);
  }
}
