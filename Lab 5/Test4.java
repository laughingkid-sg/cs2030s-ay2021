import cs2030s.fp.BooleanCondition;
import cs2030s.fp.Maybe;
import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;

class Test4 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expect("Maybe.<Number>none().orElse(4)",
        Maybe.<Number>none().orElse(4), 4);
    i.expect("Maybe.<Integer>some(1).orElse(4)",
        Maybe.<Integer>some(1).orElse(4), 1);

    Producer<Double> zero = new Producer<>() {
      public Double produce() {
        return 0.0;
      }
    };
    i.expect("Maybe.<Number>none().orElseGet(zero);",
        Maybe.<Number>none().orElseGet(zero), 0.0);
    i.expect("Maybe.<Number>some(1).orElseGet(zero);",
        Maybe.<Number>some(1).orElseGet(zero), 1);
  }
}
