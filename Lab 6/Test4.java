import cs2030s.fp.Combiner;
import cs2030s.fp.Lazy;
import cs2030s.fp.Transformer;
import java.util.ArrayList;
import java.util.List;

/**
 * Test 4 for CS2030S Lab 2 (AY20/21 Sem 2).  Tests
 * for Lazy combine.
 *
 * @author Ooi Wei Tsang
 */
class Test4 {
  /**
   * Main method for Test4.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    Lazy<Integer> ten = Lazy.of(10);
    Lazy<Integer> five = Lazy.of(5);
    Lazy<Integer> fifty = five.combine(ten, (x, y) -> x * y);
    i.expect("Lazy<Integer> fifty = Lazy.of(5).combine(Lazy.of(10), (x, y) -> x * y);\n" +
        "fifty.toString() returns ?",
        fifty.toString(), "?");
    i.expect("fifty.get() returns 50",
        fifty.get(), 50);
    Lazy<Integer> hundred = fifty.combine(fifty, (x, y) -> x + y);
    i.expect("fifty.combine(fifty, (x, y) -> x + y).toString() returns ?",
        hundred.toString(), "?");
    i.expect("fifty.combine(fifty, (x, y) -> x + y).toString() returns ?",
        hundred.get(), 100);

    Combiner<Integer, Double, String> f = (x, y) -> Integer.toString(x) + " " + Double.toString(y);
    Lazy<String> str = Lazy.of(10).combine(Lazy.of(0.01), f);
    i.expect("Lazy<String> str = Lazy.of(10).combine(Lazy.of(0.01), (x, y) -> x + \" \" + y)\n" + 
        "str.toString() returns ?",
        str.toString(), "?");
    i.expect("s.toString() returns ? again",
        str.toString(), "?");
    i.expect("s.get() returns \"10 0.01\"",
        str.get(), "10 0.01");
  }
}
