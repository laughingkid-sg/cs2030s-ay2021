import cs2030s.fp.BooleanCondition;
import cs2030s.fp.InfiniteList;
import cs2030s.fp.Transformer;
import java.util.ArrayList;
import java.util.List;

/**
 * Test 5 for CS2030S Lab 7 (AY20/21 Sem 2).  Tests
 * for InfiniteList takeWhile()
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

    List<Integer> incrHistory = new ArrayList<>();
    Transformer<Integer, Integer> incr = x -> { 
      incrHistory.add(x);
      return x + 1;
    };

    List<Integer> lessThan0History = new ArrayList<>();
    BooleanCondition<Integer> lessThan0 = x -> { 
      lessThan0History.add(x);
      return x < 0;
    };

    i.expect("InfiniteList.<Integer>empty().takeWhile(x -> x < 0).isEmpty()\n" +
        " ..returns true",
        () -> InfiniteList.<Integer>empty().takeWhile(x -> x < 0).isEmpty(), true);

    i.expect("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 0).isEmpty()\n" +
        " ..returns false",
        () -> InfiniteList.iterate(1, incr).takeWhile(lessThan0).isEmpty(), false);
    i.expect(" ..causes zero evaluation of x -> x + 1",
        incrHistory, List.of());
    i.expect(" ..causes zero evaluation of x -> x < 0",
        lessThan0History, List.of());

    List<Integer> lessThan2History = new ArrayList<>();
    BooleanCondition<Integer> lessThan2 = x -> { 
      lessThan2History.add(x);
      return x < 2;
    };

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    i.expect("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 2).isEmpty()\n" +
        " ..returns false",
        () -> InfiniteList.iterate(1, incr).takeWhile(lessThan2).isEmpty(), false);
    i.expect(" ..causes zero evaluation of x -> x + 1",
        incrHistory, List.of());
    i.expect(" ..causes zero evaluation of x -> x < 2",
        lessThan2History, List.of());

    i.expect("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 5).takeWhile(x -> x < 3)" +
        ".toList()\n" + " ..returns [1, 2]",
        () -> InfiniteList.iterate(1, incr).takeWhile(x -> x < 5).takeWhile(x -> x < 3).toList(), 
        List.of(1, 2));
    i.expect("InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).takeWhile(x -> x < 10)" +
        ".toList()\n " + " ..returns [2, 4, 6, 8]",
        () -> InfiniteList.iterate(1, incr).filter(x -> x % 2 == 0).takeWhile(x -> x < 10).toList(),
        List.of(2, 4, 6, 8));

    incrHistory.retainAll(List.of());
    lessThan0History.retainAll(List.of());
    i.expectException("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 0).head()\n" + 
        " ..throws exception",
        () -> InfiniteList.iterate(1, incr).takeWhile(lessThan0).head(), 
        new java.util.NoSuchElementException());
    i.expect(" ..causes zero evaluation of x -> x + 1",
        incrHistory, List.of());
    i.expect(" ..causes one evaluation of x -> x < 0",
        lessThan0History, List.of(1));

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    i.expect("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 2).head()\n" +
        " ..returns 1",
        () -> InfiniteList.iterate(1, incr).takeWhile(lessThan2).head(), 1);
    i.expect("..causes zero evaluation of x -> x + 1",
        incrHistory, List.of());
    i.expect("..causes one evaluation of x -> x < 2",
        lessThan2History, List.of(1));

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    i.expectException("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 2).tail().head()\n" +
        " ..throws exception",
        () -> InfiniteList.iterate(1, incr).takeWhile(lessThan2).tail().head(), 
        new java.util.NoSuchElementException());
    i.expect(" ..causes one evaluation of x -> x + 1",
        incrHistory, List.of(1)); 
    i.expect(" ..causes two evaluation of x -> x < 2",
        lessThan2History, List.of(1, 2)); 

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    lessThan0History.retainAll(List.of());
    i.expectException("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 2)" + 
        ".takeWhile(x -> x < 0).head()\n" +
        " ..throws exception", 
        () -> InfiniteList.iterate(1, incr).takeWhile(lessThan2).takeWhile(lessThan0).head(), 
        new java.util.NoSuchElementException());
    i.expect(" ..causes zero evaluation of x -> x + 1",
        incrHistory, List.of()); 
    i.expect(" ..causes one evaluation of x -> x < 2",
        lessThan2History, List.of(1)); 
    i.expect(" ..causes one evaluation of x -> x < 0",
        lessThan0History, List.of(1)); 

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    lessThan0History.retainAll(List.of());
    i.expectException("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 0)" + 
        ".takeWhile(x -> x < 2).head()\n" +
        " ..throws exception", 
        () -> InfiniteList.iterate(1, incr).takeWhile(lessThan0).takeWhile(lessThan2).head(), 
        new java.util.NoSuchElementException());
    i.expect(" ..causes zero evaluation of x -> x + 1",
        incrHistory, List.of()); 
    i.expect(" ..causes one evaluation of x -> x < 0",
        lessThan0History, List.of(1)); 
    i.expect(" ..causes zero evaluation of x -> x < 2",
        lessThan2History, List.of()); 

    List<Integer> lessThan5History = new ArrayList<>();
    BooleanCondition<Integer> lessThan5 = x -> { 
      lessThan5History.add(x);
      return x < 5;
    };

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    i.expectException("InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 5)" +
        ".takeWhile(x -> x < 2).tail().head()\n" +
        " ..throws exception",
        () -> InfiniteList.iterate(1, incr).takeWhile(lessThan5).takeWhile(lessThan2).tail().head(),
        new java.util.NoSuchElementException());
    i.expect(" ..causes one evaluation of x -> x + 1",
        incrHistory, List.of(1)); 
    i.expect(" ..causes two evaluations of x -> x < 5",
        lessThan5History, List.of(1, 2)); 
    i.expect(" ..causes two evaluations of x -> x < 2",
        lessThan2History, List.of(1, 2)); 

    List<Integer> isEvenHistory = new ArrayList<>();
    BooleanCondition<Integer> isEven = x -> { 
      isEvenHistory.add(x);
      return x % 2 == 0;
    };

    List<Integer> lessThan10History = new ArrayList<>();
    BooleanCondition<Integer> lessThan10 = x -> { 
      lessThan10History.add(x);
      return x < 10;
    };
    incrHistory.retainAll(List.of());
    lessThan10History.retainAll(List.of());
    i.expect("InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0)" +
        ".takeWhile(x -> x < 10).head()\n" +
        " ..returns 2",
        () -> InfiniteList.iterate(1, incr).filter(isEven).takeWhile(lessThan10).head(), 2);
    i.expect(" ..causes one evaluation of x -> x + 1",
        incrHistory, List.of(1)); 
    i.expect(" ..causes two evaluations of x -> x % 2 == 0",
        isEvenHistory, List.of(1, 2)); 
    i.expect(" ..causes one evaluations of x -> x < 10",
        lessThan10History, List.of(2)); 

    incrHistory.retainAll(List.of());
    isEvenHistory.retainAll(List.of());
    lessThan10History.retainAll(List.of());
    i.expect("InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0)" +
        ".takeWhile(x -> x < 10).tail().head()\n" +
        " ..returns 4",
        () -> InfiniteList.iterate(1, incr).filter(isEven).takeWhile(lessThan10)
        .tail().head(), 4);
    i.expect(" ..causes three evaluations of x -> x + 1",
        incrHistory, List.of(1, 2, 3)); 
    i.expect(" ..causes four evaluations of x -> x % 2 == 0",
        isEvenHistory, List.of(1, 2, 3, 4)); 
    i.expect(" ..causes two evaluations of x -> x < 10",
        lessThan10History, List.of(2, 4)); 

    incrHistory.retainAll(List.of());
    lessThan10History.retainAll(List.of());
    InfiniteList<Integer> list = InfiniteList.iterate(1, incr).takeWhile(lessThan10);
    i.expect("InifiniteList<Integer> list = InfiniteList.iterate(1, x -> x + 1)" +
        ".takeWhile(x -> x < 10)\n" +
        " ..list.tail().tail().head() returns 3",
        list.tail().tail().head(), 3);
    list.head();
    i.expect(" ..list.head() causes zero evaluation of x -> x < 10",
        lessThan10History, List.of(1, 2, 3));
    list.tail().head();
    i.expect(" ..list.tail().head() causes zero evaluation of x -> x < 10",
        lessThan10History, List.of(1, 2, 3));
    list.tail().tail().tail().head();
    i.expect(" ..list.tail().tail().tail().head() causes one evaluation of x -> x < 10",
        lessThan10History, List.of(1, 2, 3, 4));
  }
}
