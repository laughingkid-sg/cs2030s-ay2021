import cs2030s.fp.Try;

class Test1 {
  public static void main(String[] args) throws Throwable {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Try.success(1).get()", 
        () -> Try.success(1).get(),
        1);

    i.expectException("Try.failure(new Error()).get()", 
        () -> Try.failure(new Error()).get(),
        new Error());

    i.expectCompile("Try.<Number>of((Producer<Integer>) () -> 2) compiles without error",
        "cs2030s.fp.Producer<Integer> p = () -> 2;" + 
        "cs2030s.fp.Try<Number> t = cs2030s.fp.Try.of(p);",
        true);

    i.expectReturn("Try.of(() -> 2).get()", 
        () -> Try.of(() -> 4 / 2).get(),
        2);

    i.expectException("Try.of(() -> 4 / 0).get()", 
        () -> Try.of(() -> 4 / 0).get(),
        new ArithmeticException());

    i.expectReturn("Try.success(3).equals(Try.success(3))",
        () -> Try.success(3).equals(Try.success(3)),
        true);

    i.expectReturn("Try.success(null).equals(Try.success(null))",
        () -> Try.success(null).equals(Try.success(null)),
        true);

    i.expectReturn("Try.success(3).equals(Try.success(null))",
        () -> Try.success(3).equals(Try.success(null)),
        false);

    i.expectReturn("Try.success(null).equals(Try.success(3))",
        () -> Try.success(null).equals(Try.success(3)),
        false);

    i.expectReturn("Try.success(3).equals(Try.success(\"3\"))",
        () -> Try.success(3).equals(Try.success("3")),
        false);

    i.expectReturn("Try.success(3).equals(3)",
        () -> Try.success(3).equals(3),
        false);

    i.expectReturn("Try.failure(new Error()).equals(new Error())",
        () -> Try.failure(new Error()).equals(new Error()),
        false);

    i.expectReturn("Try.failure(new Error()).equals(Try.success(new Error())",
        () -> Try.failure(new Error()).equals(Try.success(new Error())),
        false);

    i.expectReturn("Try.success(new Error()).equals(Try.failure(new Error())",
        () -> Try.success(new Error()).equals(Try.failure(new Error())),
        false);

    i.expectReturn("Try.failure(new Error()).equals(Try.failure(new Error())",
        () -> Try.failure(new ArithmeticException()).equals(Try.failure(new Error())),
        false);

    i.expectReturn("Try.failure(new Error()).equals(Try.failure(new Error())",
        () -> Try.failure(new ArithmeticException()).equals(Try.failure(new ArithmeticException())),
        true);
  }
}
