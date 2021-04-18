import cs2030s.fp.BooleanCondition;
import cs2030s.fp.InfiniteList;
import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;
import java.util.ArrayList;
import java.util.List;

/**
 * Test 2 for CS2030S Lab 7 (AY20/21 Sem 2).  Tests
 * for InfiniteList map()
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

    i.expectReturn(
        "InfiniteList.generate(() -> 1).map(x -> x * 2).head()",
        () -> InfiniteList.generate(() -> 1).map(x -> x * 2).head(), 2);
    i.expectReturn(
        "InfiniteList.generate(() -> 1).map(x -> x * 2).tail().head()",
        () -> InfiniteList.generate(() -> 1).map(x -> x * 2).tail().head(), 2);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).head(), 2);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).tail().head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).tail().head(), 4);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).head(), 1);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).tail().head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).tail().head(), 3);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x % 2 == 0 ? null : x).tail().head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x % 2 == 0 ? null : x).tail().head(),
        null);

    List<Integer> doublerHistory = new ArrayList<>();
    List<Integer> generateHistory = new ArrayList<>();
    Producer<Integer> generator = () -> { 
      generateHistory.add(1); 
      return 1; 
    };
    Transformer<Integer, Integer> doubler = x -> { 
      doublerHistory.add(x); 
      return x * 2; 
    };
    // Transformer<Integer,Integer> oneLess = x -> { doublerHistory.add(x); return x - 1; };

    i.expect("InfiniteList.generate(() -> 1).map(x -> x * 2).tail().head()\n" +
        " ..returns 2",
        () -> InfiniteList.generate(generator).map(doubler).tail().head(), 2);
    i.expect(" ..causes one eval of () -> 1",
        generateHistory, List.of(1, 1));
    i.expect(" ..causes one eval of x -> x * 2",
        doublerHistory, List.of(1, 1));

    generateHistory.retainAll(List.of());
    doublerHistory.retainAll(List.of());
    InfiniteList<Integer> ones = InfiniteList.generate(generator);
    InfiniteList<Integer> twos = ones.map(doubler);
    twos.tail().head();
    i.expect("InfiniteList<Integer> ones = InfiniteList.generate(() -> 1)\n" + 
        "InfiniteList<Integer> twos = ones.map(x -> x * 2)\n" + 
        "After twos.tail().head()\n" +
        " ..ones.toString() returns [[1] [[1] ?]]",
        () -> ones.toString(), "[[1] [[1] ?]]");
    i.expect(" ..twos.toString() returns [[2] [[2] ?]]",
        () -> twos.toString(), "[[2] [[2] ?]]");
    twos.head();
    i.expect(" ..calling twos.head() again\n" + 
        " ....causes zero evaluation of () -> 1",
        generateHistory, List.of(1, 1));
    i.expect(" ....causes zero evaluation of x -> x * 2",
        doublerHistory, List.of(1, 1));
    i.expect(" ..calling twos.tail().head() again\n" +
        " ....causes zero evaluation of () -> 1",
        generateHistory, List.of(1, 1));
    i.expect(" ....causes zero evaluation of x -> x * 2",
        doublerHistory, List.of(1, 1));

  }
}
