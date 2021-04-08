import cs2030s.fp.BooleanCondition;
import cs2030s.fp.Lazy;
import java.util.ArrayList;
import java.util.List;

/**
 * Test 3 for CS2030S Lab 2 (AY20/21 Sem 2).  Tests
 * for filter and equals.
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

    Lazy<Boolean> even = Lazy.of(50).filter(x -> x % 2 == 0);
    i.expect("Lazy.of(50).filter(x -> x % 2 == 0).toString() returns ?",
        even.toString(), "?");
    i.expect("Lazy.of(50).filter(x -> x % 2 == 0).get() returns true",
        even.get(), true);

    i.expect("Lazy.of(50).equals(Lazy.of(5).map(x -> x * 10)) returns true",
        Lazy.of(50).equals(Lazy.of(5).map(x -> x * 10)), true);
    i.expect("Lazy.of(50).equals(50)",
        Lazy.of(50).equals(50), false);
    i.expect("Lazy.of(50).equals(Lazy.of(\"50\")",
        Lazy.of(50).equals(Lazy.of("50")), false);
    i.expect("Lazy.of(50).filter(i -> i % 2 == 0).equals(Lazy.of(true))",
        Lazy.of(50).filter(x -> x % 2 == 0).equals(Lazy.of(true)), true);

    List<Integer> numOfEvals = new ArrayList<>();
    BooleanCondition<String> isHello = s -> {
      numOfEvals.add(1);
      return s.equals("hello");
    };
    Lazy<Boolean> same = Lazy.of("hi").filter(isHello);
    i.expect("Lazy.of(\"hi\").filter(isHello).toString() returns ?", 
        same.toString(), "?");
    i.expect("Lazy.of(\"hi\").filter(isHello).toString() does not evaluate the boolean condition", 
        numOfEvals.size(), 0);
    i.expect("Lazy.of(\"hi\").filter(isHello).get() returns false", 
        same.get(), false);
    i.expect("Lazy.of(\"hi\").filter(isHello).get() evaluates the boolean condition once", 
        numOfEvals.size(), 1);
    i.expect("Lazy.of(\"hi\").filter(isHello).get() does not evaluate the boolean condition again", 
        numOfEvals.size(), 1);
  }
}
