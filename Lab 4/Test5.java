class Test5 {

  public static void main(String[] args) {

    class AddOne implements Transformer<Integer, Integer> {
      @Override
      public Integer transform(Integer t) { 
        return t + 1; 
      }
    }

    CS2030STest i = new CS2030STest();

    i.expect("Box.of(4).map(new AddOne()) becomes Box.of(5)",
        Box.of(4).map(new AddOne()), Box.of(5));

    i.expect("Box.<Integer>empty().map(new AddOne()) is still Box.empty()", 
        Box.<Integer>empty().map(new AddOne()), Box.empty());

    i.expectCompile("Box<Integer> b = Box.of(4).map(new AddOne()) compiles cleanly",
        "Box<Integer> b = Box.of(4).map(new Transformer<Integer,Integer>() { public Integer transform(Integer t) { return t; } })",
        true);

    class StringLength implements Transformer<String, Integer> {
      @Override
      public Integer transform(String t) { 
        return t.length(); 
      }
    }

    i.expect("Box.of(\"string\").map(new StringLength()) becomes Box.of(6)", 
        Box.of("string").map(new StringLength()), Box.of(6));

    i.expectCompile("Box<Integer> b = Box.of(\"string\").map(new StringLength()) compiles cleanly",
        "Box<Integer> b = Box.of(\"string\").map(new Transformer<String,Integer>(){public Integer transform(String t){return t.length();}})", 
        true);

    i.expect("Box.of(\"string\").map(new StringLength()).map(new AddOne()) becomes Box.of(7)",
        Box.of("string").map(new StringLength()).map(new AddOne()), Box.of(7));

    i.expect("Box.of(\"string\").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne()) becomes empty", 
        Box.of("string").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne()), Box.empty());

    i.expect("Box.of(\"chocolates\").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne()) becomes Box.of(11)", 
        Box.of("chocolates").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne()), Box.of(11));

    i.expect("Box.<String>empty().map(new StringLength()) is still empty", 
        Box.<String>empty().map(new StringLength()), Box.empty());

    class AlwaysNull implements Transformer<Integer, Object> {
      @Override
      public Object transform(Integer t) { 
        return null; 
      }
    }

    i.expect("Box.of(4).map(new AlwaysNull()) becomes empty", 
        Box.of(4).map(new AlwaysNull()), Box.empty());

    i.expectCompile("Box<Object> o = Box.of(4).map(new AlwaysNull()) compiles cleanly",
        "Box<Object> o = Box.of(4).map(new Transformer<Integer,Object>() { public Object transform(Integer t) { return null; }})",
        true);

    i.expect("new LastDigitsOfHashCode(4).transform(\"string\") is 5903", 
        new LastDigitsOfHashCode(4).transform("string"), 5903);

    i.expect("new LastDigitsOfHashCode(4).transform(123456) is 3456", 
        new LastDigitsOfHashCode(4).transform(123456), 3456);

    i.expect("Box.of(\"string\").map(new LastDigitsOfHashCode(2)) becomes Box.of(3)", 
        Box.of("string").map(new LastDigitsOfHashCode(2)), Box.of(3));

    i.expectCompile("Box<Integer> b = Box.of(\"string\").map(new LastDigitsOfHashCode(2)) compiles cleanly",
        "Box<Integer> b = Box.of(\"string\").map(new LastDigitsOfHashCode(2))", 
        true);

    i.expect("Box.of(123456).map(new LastDigitsOfHashCode(5)) becomes Box.of(23456)", 
        Box.of(123456).map(new LastDigitsOfHashCode(5)), Box.of(23456));

    i.expectCompile("Box<B> b = Box.of(new A()).map(new AtoB()) compiles cleanly",
        "class A {} class B {} class AtoB implements Transformer<A,B> { public B transform(A t) { return new B(); }}" +
        "Box<B> b = Box.of(new A()).map(new AtoB())", 
        true);
  }
}
