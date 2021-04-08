import cs2030s.fp.Combiner;
import cs2030s.fp.Lazy;
import cs2030s.fp.Transformer;
import java.util.ArrayList;
import java.util.List;

/**
 * Test 5 for CS2030S Lab 2 (AY20/21 Sem 2).  
 * Tests LazyList.
 *
 * @author Ooi Wei Tsang
 */
class Test5 {
  /**
   * Main method for Test5.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    List<Integer> numOfEval = new ArrayList<>();
    Transformer<Integer, Integer> incr = x -> {
      numOfEval.add(1);
      return x + 1;
    };
    LazyList<Integer> l = LazyList.generate(4, 0, incr);
    i.expect("An initial LazyList only has a single evaluated element",
        l.toString(), "[0, ?, ?, ?]");
    l.indexOf(2);
    i.expect("Looking for 2 causes two evaluations",
        numOfEval.size(), 2);
    l.indexOf(1);
    i.expect("Looking for 1 does not need any evaluation",
        numOfEval.size(), 2);
    l.get(1);
    i.expect("Retrieving 1 does not need any evaluation",
        numOfEval.size(), 2);
    l.get(3);
    i.expect("Retrieving 3 causes one more evaluation",
        numOfEval.size(), 3);
  }
}
