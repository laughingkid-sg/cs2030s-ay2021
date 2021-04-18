class Test1 {

  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    // Test Box of and toString
    i.expect("Box.of(4)",
        Box.of(4).toString(), "[4]");

    // Test Box equals
    i.expect("Box.of(4) equals Box.of(4)",
        Box.of(4).equals(Box.of(4)), true);

    // Test Box equals
    i.expect("Box.of(Box.of(0)) equals Box.of(0)",
        Box.of(Box.of(0)).equals(Box.of(0)), false);

    // Test Box equals
    i.expect("Box.of(Box.of(0)) equals Box.of(Box.of(0))",
        Box.of(Box.of(0)).equals(Box.of(Box.of(0))), true);

    // Test Box of and toString
    i.expect("Box.of(string)",
        Box.of("string").toString(), "[string]");

    // Test Box equals
    i.expect("Box.of(\"string\") equals Box.of(4)",
        Box.of("string").equals(Box.of(4)), false);

    // Test Box equals
    i.expect("Box.of(\"string\") equals Box.of(\"null\")",
        Box.of("string").equals(Box.of("null")), false);

    // Test null Box
    i.expect("Box.of(null)",
        Box.of(null), null);

    // Test null Box equals
    i.expect("Box.of(null) equals Box.of(null)",
        Box.of(null), Box.of(null));

    i.expect("Box.of(new StringBuffer()).equals(Box.of(new StringBuffer())) is false", 
        Box.of(new StringBuffer()).equals(Box.of(new StringBuffer())), false);

    class A { }

    i.expect("Box.of(new A()).equals(Box.of(new A())) is false", 
        Box.of(new A()).equals(Box.of(new A())), false);

    A a = new A();
    i.expect("a = new A(); Box.of(a).equals(Box.of(a) is true", 
        Box.of(a).equals(Box.of(a)), true);

    i.expect("a = new A(); Box.of(a).equals(a) is false", 
        Box.of(a).equals(a), false);

    Box<A> b = Box.of(a);

    i.expect("b = Box.of(new A()); b.equals(Box.of(b)) is false", 
        b.equals(Box.of(b)), false);

    i.expect("b = Box.of(new A()); Box.of(b).equals(b) is false", 
        Box.of(b).equals(b), false);

    i.expect("a = new A(); Box.of(a).equals(null) is false", 
        Box.of(a).equals(null), false);
  }
}
