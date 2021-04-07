class Test4 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    SourceList<Integer> intList = new Pair<>(1, new Pair<>(2, new Pair<>(3, 
            new Pair<>(4, new EmptyList<>()))));

    i.expect("filtering 1 2 3 4 for integers larger than 2 should give 3 4 only",
        intList.filter(new GreaterThanTwo()).toString(), "3, 4, EmptyList");

    intList = new Pair<>(1, new Pair<>(2, new Pair<>(3, new Pair<>(4, new EmptyList<>()))));

    intList.filter(new GreaterThanTwo());
    i.expect("filtering should not change the original list",
        intList.toString(), "1, 2, 3, 4, EmptyList");

    i.expect("filtering should return a new list",
        intList.filter(new GreaterThanTwo()) == intList, false);

    String intListStr = String.join("",
        "SourceList<Integer> intList = new Pair<>(1,",
        "new Pair<>(2,",
        "new Pair<>(3,",
        "new Pair<>(4,", 
        "new EmptyList<>()))));");
    i.expectCompile("should be able to assign return value from the filter above " + 
        "to SourceList<Integer>",
        intListStr + "SourceList<Integer> l = intList.filter(new GreaterThanTwo());", true);

    i.expectCompile("should not be able to assign return value from the filter above " +
        "to SourceList<String>",
        intListStr + "SourceList<String> l = intList.filter(new GreaterThanTwo());", false);

    i.expect("filtering an empty list should give another emptylist",
        new EmptyList<Integer>().filter(new GreaterThanTwo()).toString(), "EmptyList");

    i.expectCompile("should be able to assign return value from the filter above " +
        "to SourceList<Integer>",
        "SourceList<Integer> l = new EmptyList<Integer>().filter(new GreaterThanTwo());", true);

    i.expectCompile("should not be able to assign return value from the filter above " +
        "to SourceList<String>",
        "SourceList<String> l = new EmptyList<Integer>().filter(new GreaterThanTwo());", false);

    i.expectCompile("should be able to chain two filters (intList.filter(..).filter(..))",
        intListStr + "intList.filter(new GreaterThanTwo()).filter(new GreaterThanTwo());", true);

    i.expectCompile("should be able to chain two filters (emptyList.filter(..).filter(..))",
        "new EmptyList<Integer>().filter(new GreaterThanTwo()).filter(new GreaterThanTwo());", 
        true);

    String a = String.join("", intListStr,
        "class A implements BooleanCondition<Object> { ", 
        "  public boolean test(Object o) {", 
        "    return false;",
        " }",
        "}", 
        "intList.filter(new A());"
        );

    i.expectCompile("should be able to filter a BooleanCondition<Object>",
        a, true);
  }
}
