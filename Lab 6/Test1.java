import cs2030s.fp.Lazy;
import cs2030s.fp.Producer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Test 1 for CS2030S Lab 2 (AY20/21 Sem 2).  Tests
 * for basic Lazy property: evaluation is delayed and is 
 * done at most once.
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

    i.expectCompile("Lazy<Integer> eight = Lazy.of(8) type checks",
        "cs2030s.fp.Lazy<Integer> eight = cs2030s.fp.Lazy.of(8)", true);
    i.expect("Lazy.of(8) has a string reprentation of 8",
        Lazy.of(8).toString(), "8");
    i.expect("Lazy.of(8).get() returns 8",
        Lazy.of(8).get(), 8);
    i.expectCompile("Lazy<String> hello = Lazy.of(() -> \"hello\") type checks",
        "cs2030s.fp.Producer<String> p = () -> \"hello\";" + 
        "cs2030s.fp.Lazy<String> hello = cs2030s.fp.Lazy.of(p);", 
        true);

    Producer<String> s = () -> "hello";
    Lazy<String> hello = Lazy.of(s);
    i.expect("hello has a string reprentation of ?",
        hello.toString(), "?");
    i.expect("hello.get() returns \"hello\"",
        hello.get(), "hello");

    List<Integer> numOfCalls = new ArrayList<>();
    // add side effects
    s = () -> { 
      numOfCalls.add(1); 
      return "hello"; 
    };

    hello = Lazy.of(s);
    hello.get();
    hello.get();
    i.expect("hello.get() calls the producer exactly once",
        numOfCalls.size(), 1);

    Random rng = new Random(1);
    Producer<Integer> r = () -> rng.nextInt();
    Lazy<Integer> random = Lazy.of(r);
    i.expect("random has a string reprentation of ?",
        random.toString(), "?");
    i.expect("random.get() twice returns the same value",
        random.get(), random.get());

    Lazy<Object> n = Lazy.of(null);
    i.expect("Lazy.of(null).toString() returns \"null\"",
        Lazy.of((Object) null).toString(), "null");
    i.expect("Lazy.of(null).get() returns null",
        Lazy.of((Object) null).get(), null);
    i.expect("Lazy.of(()->null).toString() returns ?",
        Lazy.of((Producer<Integer>) () -> null).toString(), "?");
    i.expect("Lazy.of(()->null).get() returns null",
        Lazy.of((Producer<Integer>) () -> null).get(), null);
  }
}
