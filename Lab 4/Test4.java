class Test4 {

  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    i.expect("new DivisibleBy(5).test(4) is false", 
        new DivisibleBy(5).test(4), false);

    i.expect("new DivisibleBy(5).test(10) is true", 
        new DivisibleBy(5).test(10), true);

    i.expect("Box.of(10).filter(new DivisibleBy(2)) is still the same", 
        Box.of(10).filter(new DivisibleBy(2)), Box.of(10));

    i.expectCompile("Compiling Box<Integer> b = Box.of(10).filter(new DivisibleBy(2))",
        "Box<Integer> b = Box.of(10).filter(new DivisibleBy(2))", true);

    i.expect("Box.of(3).filter(new DivisibleBy(2)) is empty", 
        Box.of(3).filter(new DivisibleBy(2)), Box.empty());

    i.expectCompile("Compiling Box<Integer> b = Box.of(3).filter(new DivisibleBy(2))",
        "Box<Integer> b = Box.of(3).filter(new DivisibleBy(2))", true);

    i.expect("Box.empty().filter(new DivisibleBy(10)) is empty",
        Box.<Integer>empty().filter(new DivisibleBy(10)), Box.<Integer>empty());

    i.expect("new LongerThan(6).test(\"123456\") is false",
        new LongerThan(6).test("123456"), false);

    i.expect("new LongerThan(3).test(\"\") is false",
        new LongerThan(3).test(""), false);

    i.expect("Box.of(\"\").filter(new LongerThan(10)) is empty",
        Box.of("").filter(new LongerThan(10)), Box.<String>empty());

    i.expect("Box.of(\"123456789\").filter(new LongerThan(10)) is empty",
        Box.of("123456789").filter(new LongerThan(10)), Box.<String>empty());

    i.expect("Box.of(\"1234567890\").filter(new LongerThan(10)) is empty",
        Box.of("1234567890").filter(new LongerThan(10)), Box.<String>empty());

    i.expect("Box.of(\"1234567890A\").filter(new LongerThan(10)) is still the same", 
        Box.of("1234567890A").filter(new LongerThan(10)), Box.<String>of("1234567890A"));

    i.expect("Box.<String>empty().filter(new LongerThan(10)) is empty", 
        Box.<String>empty().filter(new LongerThan(10)), Box.<String>empty());

    i.expectCompile("Compiling Box.of(\"hello\").filter(new DivisibleBy(10)) should give error",
        "Box.of(\"hello\").filter(new DivisibleBy(10))", false);
  }
}
