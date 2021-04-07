class Test5 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    SourceList<Integer> intList;
    intList = new Pair<>(1, new Pair<>(2, new Pair<>(3, new Pair<>(4, new EmptyList<>()))));
    i.expect("mapping 1 2 3 4 from integers to string",
        intList.map(new IntegerToString()).toString(), "\"1\", \"2\", \"3\", \"4\", EmptyList");

    intList = new Pair<>(1, new Pair<>(2, new Pair<>(3, new Pair<>(4, new EmptyList<>()))));
    intList.map(new IntegerToString());
    i.expect("mapping should not change the original list",
        intList.toString(), "1, 2, 3, 4, EmptyList");

    String intListStr = String.join("",
        "SourceList<Integer> intList = new Pair<>(1,",
        "new Pair<>(2,",
        "new Pair<>(3,",
        "new Pair<>(4,", 
        "new EmptyList<>()))));");

    i.expectCompile(
        "should be able to assign return value from the map above to SourceList<String>",
        intListStr + "SourceList<String> l = intList.map(new IntegerToString());", true);

    i.expectCompile(
        "should not be able to assign return value from the map above to SourceList<Integer>",
        intListStr + "SourceList<Integer> l = intList.map(new IntegerToString());", false);

    i.expect("mapping an empty list should give another emptylist",
        new EmptyList<Integer>().map(new IntegerToString()).toString(), "EmptyList");

    i.expectCompile(
        "should be able to assign return value from the map above to SourceList<String>",
        "SourceList<String> l = new EmptyList<Integer>().map(new IntegerToString());", true);

    i.expectCompile(
        "should not be able to assign return value from the filter above to SourceList<Integer>",
        "SourceList<Integer> l = new EmptyList<Integer>.map(new IntegerToString());", false);

    SourceList<String> strList;
    strList = new Pair<>("AA", new Pair<>("A", new Pair<>("", new EmptyList<>())));

    String strListStr = String.join("",
        "SourceList<String> strList = new Pair<>(\"AA\", ",
        "new Pair<>(\"A\", ",
        "new Pair<>(\"\", ",
        "new EmptyList<>())));"); 

    i.expect("mapping \"AA\" \"A\" \"\" from string to integer",
        strList.map(new StringToLength()).toString(), "2, 1, 0, EmptyList");

    i.expectCompile(
        "should be able to assign return value from the map above to SourceList<Integer>",
        strListStr + "SourceList<Integer> l = strList.map(new StringToLength());", true);

    i.expectCompile(
        "should not be able to assign return value from the map above to SourceList<String>",
        strListStr + "SourceList<String> l = strList.map(new StringToLength());", false);

    i.expectCompile(
        "should be able to chain two maps (intList.map(..).map(..))",
        intListStr + "intList.map(new IntegerToString()).map(new StringToLength())", true);

    i.expectCompile(
        "should be able to chain two maps (emptyList.map(..).map(..))",
        "new EmptyList<Integer>().map(new IntegerToString()).map(new StringToLength())", true);

    i.expectCompile(
        "should be able to chain map and filter (strList.map(..).filter(..))",
        strListStr + "strList.map(new StringToLength()).filter(new GreaterThanTwo())", true);

    i.expectCompile(
        "should be able to chain filter and map (intList.filter(..).map(..))",
        intListStr + "intList.filter(new GreaterThanTwo()).map(new IntegerToString())", true);

    String a = String.join("", 
        strListStr,
        "class A implements Transformer<Object,Integer> { ", 
        "  public Integer transform(Object o) {", 
        "    return 0;", 
        "  }",
        "}",
        "SourceList<Object> l = strList.map(new A());");
    i.expectCompile(
        "should be able to map Transformer<Object,Integer> and assign to SourceList<Object>",
        a, true);
  }
}

