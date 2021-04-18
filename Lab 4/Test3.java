class Test3 {

  public static void main(String[] args) {

    class AlwaysTrue<T> implements BooleanCondition<T> {
      @Override
      public boolean test(T t) { 
        return true; 
      }
    }

    class AlwaysFalse<T> implements BooleanCondition<T> {
      @Override
      public boolean test(T t) { 
        return false; 
      }
    }

    CS2030STest i = new CS2030STest();

    i.expect("Box.of(4).filter(new AlwaysTrue<>()) is Box.of(4)",
        Box.<Integer>of(4).filter(new AlwaysTrue<>()), Box.of(4));

    i.expect("Box.empty().filter(new AlwaysTrue<>()) is empty", 
        Box.empty().filter(new AlwaysTrue<>()), Box.empty());

    i.expect("Box.of(\"string\").filter(new AlwaysFalse<>()) is empty",
        Box.<String>of("string").filter(new AlwaysFalse<>()), Box.empty());

    i.expect("Box.empty().filter(new AlwaysFalse<>()) is empty", 
        Box.empty().filter(new AlwaysFalse<>()), Box.empty());

    class A { }
    i.expect("Box.of(new A()).filter(new AlwaysFalse<>()) is empty",
        Box.<A>of(new A()).filter(new AlwaysFalse<>()), Box.empty());

    class IntValueIsPositive implements BooleanCondition<Number> {
      public boolean test(Number t) { return t.intValue() > 0; }
    }

    i.expect("Box.<Double>of(8.8).filter(new InvalueIsPositive()) does not change",
        Box.<Double>of(8.8).filter(new IntValueIsPositive()), Box.of(8.8));

    i.expect("Box.<Double>of(8.8).filter(new IntValueIsPositive()).filter(new IntValueIsPositive()) does not change",
        Box.<Double>of(8.8).filter(new IntValueIsPositive()).filter(new IntValueIsPositive()), Box.of(8.8));

    i.expect("Box.<Long>of(-100L).filter(new IntValueIsPositive()).filter(new IntValueIsPositive()) is empty",
        Box.<Long>ofNullable(-100L).filter(new IntValueIsPositive()).filter(new IntValueIsPositive()), Box.empty());
  }
}
