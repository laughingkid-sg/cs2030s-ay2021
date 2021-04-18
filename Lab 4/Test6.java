class Test6 {

  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expect("Box.of(4).map(new BoxIt<>())) is Box.of(Box.of(4))",
        Box.of(4).map(new BoxIt<>()), Box.of(Box.of(4)));

    i.expect("Box.of(Box.of(5)).map(new BoxIt<>())) is Box.of(Box.of(Box.of(5)))",
        Box.of(Box.of(5)).map(new BoxIt<>()), Box.of(Box.of(Box.of(5))));

    i.expect("Box.ofNullable(null).map(new BoxIt<>()) is still Box.empty())",
        Box.ofNullable(null).map(new BoxIt<>()), Box.empty());

    i.expectCompile("Box<Box<A>> a = Box.of(new A()).map(new BoxIt<>()) compiles cleanly",
        "class A {} Box<Box<A>> a = Box.of(new A()).map(new BoxIt<>())",
        true);

    i.expectCompile("Box<Box<Box<A>>> a = Box.of(Box.of(new A())).map(new BoxIt<>()) compiles cleanly",
        "class A {} Box<Box<Box<A>>> a = Box.of(Box.of(new A())).map(new BoxIt<>())",
        true);
  }
}
