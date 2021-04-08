import cs2030s.fp.Lazy;
import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;
import java.util.ArrayList;
import java.util.List;

/**
 * Test 2 for CS2030S Lab 2 (AY20/21 Sem 2).  Tests
 * for map and flatMap of Lazy.
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

    Producer<String> producer = () -> "123456";
    Lazy<String> lazy = Lazy.of(producer);
    i.expect("Lazy<String> lazy = Lazy.of(() -> \"123456\")\n" +
        "lazy.map(s -> s.substring(0, 1)).toString() returns ?",
        lazy.map(s -> s.substring(0, 1)).toString(), "?");
    i.expect("lazy.toString() returns \"?\"",
        lazy.toString(), "?");
    i.expect("lazy.map(s -> s.substring(0, 1)).get() returns \"1\"",
        lazy.map(s -> s.substring(0, 1)).get(), "1");
    i.expect("lazy.toString() returns \"123456\"",
        lazy.toString(), "123456");

    List<Integer> numOfProduces = new ArrayList<>();
    List<Integer> numOfMaps = new ArrayList<>();
    producer = () -> { 
      numOfProduces.add(1); 
      return "123456"; 
    };
    Transformer<String, String> substr;
    substr = str -> { 
      numOfMaps.add(1); 
      return str.substring(0, 1); 
    };
    lazy = Lazy.of(producer);
    lazy = lazy.map(substr);
    lazy.get();
    lazy.get();
    i.expect("lazy.get(); lazy.get(); calls producer exactly once",
        numOfProduces.size(), 1); 
    i.expect("lazy.get(); lazy.get(); calls transformer exactly once",
        numOfMaps.size(), 1); 

    Lazy<Integer> lazyInt = Lazy.of(10);
    lazyInt = lazyInt.map(k -> k + 1);
    lazyInt = lazyInt.flatMap(j -> Lazy.of(j + 3));
    i.expect("Lazy.of(10).map(i -> i + 1).flatMap(j -> Lazy.of(j + 3)).toString() returns ?",
        lazyInt.toString(), "?");
    i.expect("Lazy.of(10).map(i -> i + 1).flatMap(j -> Lazy.of(j + 3)).get() returns 14",
        lazyInt.get(), 14);
  }
}
