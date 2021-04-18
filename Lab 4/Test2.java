class Test2 {

  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    // Test ofNullable
    i.expect("Box.ofNullable(4) equals Box.of(4)", 
        Box.ofNullable(4), Box.of(4));

    i.expect("Box.ofNullable(string) equals Box.of(\"string\")",
        Box.ofNullable("string"), Box.of("string"));

    i.expect("Box.ofNullable(null).toString() is [] ",
        Box.ofNullable(null).toString(), "[]");

    i.expect("Box.empty() == Box.empty() is true",
        Box.empty() == Box.empty(), true);

    i.expect("Box.empty().equals(Box.empty()) is true",
        Box.empty().equals(Box.empty()), true);

    i.expect("Box.ofNullable(null) == Box.empty() is true",
        Box.ofNullable(null) == Box.empty(), true);

    i.expect("Box.ofNullable(null).equals(Box.empty()) is true",
        Box.ofNullable(null).equals(Box.empty()), true);

    i.expect("Box.ofNullable(\"string\").isPresent() is true", 
        Box.ofNullable("string").isPresent(), true);

    i.expect("Box.ofNullable(null).isPresent() is false", 
        Box.ofNullable(null).isPresent(), false);

    class A { }

    i.expect("Box.ofNullable(null).equals(Box.of(new A())) is false", 
        Box.ofNullable(null).equals(Box.of(new A())), false);

    i.expect("Box.of(new A()).equals(Box.ofNullable(null)) is false",
        Box.of(new A()).equals(Box.ofNullable(null)), false);

    i.expect("Box.empty().equals(Box.of(new A())) is false",
        Box.empty().equals(Box.of(new A())), false);

    i.expect("Box.of(new A()).equals(Box.empty()) is false",
        Box.of(new A()).equals(Box.empty()), false);

    i.expect("Box.<A>ofNullable(null).isPresent() is false", 
        Box.<A>ofNullable(null).isPresent(), false);

    i.expect("Box.<A>ofNullable(new A()).isPresent() is true", 
        Box.<A>ofNullable(new A()).isPresent(), true);
  }

}
