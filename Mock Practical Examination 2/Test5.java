/**
 * Test 5 for CS2030S Mock PE 2 (AY20/21 Sem 2).  Tests
 * for uses of PECS in map and flatMap
 *
 * @author Ooi Wei Tsang
 */
class Test5 {
  /**
   * Main method for Test5.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    i.expectCompile("map uses PECS",
        "Transformer<Object,Integer> f = x -> x.hashCode();\n" +
        "Trace<Number> t = Trace.<Number>of(23.6).map(f)", true);
    i.expectCompile("flatMap uses PECS",
        "Transformer<Object, SubTrace<Integer>> g = x -> SubTrace.of(x.hashCode());\n" +
        "Trace<Number> t = Trace.<Number>of(23.6).flatMap(g)", true);
  }
}
