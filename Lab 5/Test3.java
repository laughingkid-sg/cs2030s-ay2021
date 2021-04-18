import cs2030s.fp.BooleanCondition;
import cs2030s.fp.Maybe;
import cs2030s.fp.Transformer;
import java.util.Map;

class Test3 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    Map<String, Integer> map = Map.of("one", 1, "two", 2);
    Transformer<String, Maybe<Integer>> wordToMaybeInt = new Transformer<>() {
      public Maybe<Integer> transform(String x) {
        return Maybe.of(map.get(x));
      }
    };

    i.expect("Maybe.<String>none().flatMap(wordToMaybeInt)",
        Maybe.<String>none().flatMap(wordToMaybeInt), Maybe.none());
    i.expect("Maybe.<String>some(\"\").flatMap(wordToMaybeInt)",
        Maybe.<String>some("").flatMap(wordToMaybeInt), Maybe.none());
    i.expect("Maybe.<String>some(\"one\").flatMap(wordToMaybeInt)",
        Maybe.<String>some("one").flatMap(wordToMaybeInt), Maybe.some(1));
    i.expectCompileWithImport("import cs2030s.fp.*;",
        "Maybe<Number> m = Maybe.<String>some(\"one\").flatMap(t) should compile",
        "Transformer<String,Maybe<Integer>> t = new Transformer<>() {" +
        "  public Maybe<Integer> transform(String x) {" +
        "    return Maybe.of(1);" +
        "  }" +
        "};" +
        "Maybe<Number> m = Maybe.<String>some(\"one\").flatMap(t)", true);
  }
}
