import cs2030s.fp.Transformer;
import cs2030s.fp.Try;
import java.io.IOException;

class Test2 {
  public static void main(String[] args) throws Throwable {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Try.success(4).map(x -> x + 1).get()",
        () -> Try.success(4).map(x -> x + 1).get(),
        5);

    i.expectException("Try.failure(new NullPointerException()).map(x -> x.toString()).get()",
        () -> Try.<Integer>failure(new NullPointerException()).map(x -> x.toString()).get(),
        new NullPointerException());

    i.expectReturn("Try.failure(new IOException()).map(x -> x.toString()).equals(Try.failure(new IOException()))",
        () -> Try.failure(new IOException()).map(x -> x.toString()).equals(Try.failure(new IOException())),
        true);

    i.expectReturn("Try.success(4).map(x -> 8 / x).map(x -> x + 1).get()",
        () -> Try.success(4).map(x -> 8 / x).map(x -> x + 1).get(),
        3);

    i.expectException("Try.success(0).map(x -> 8 / x).map(x -> x + 1).get()",
        () -> Try.success(0).map(x -> 8 / x).map(x -> x + 1).get(),
        new ArithmeticException());

    i.expectCompile(
        "Transformer<Object, Integer> hash = x -> x.hashCode();\n" +
        "Try<Number> t = Try.success(\"hello\").map(hash); compiles without error",
        "cs2030s.fp.Transformer<Object, Integer> hash = x -> x.hashCode();" +
        "cs2030s.fp.Try.success(\"hello\").map(hash);",
        true);

    i.expectException(
        "Try.<Integer>success(null).map(x -> x.hashCode()).get()",
        () -> Try.success(null).map(x -> x.hashCode()).get(),
        new NullPointerException());
  }

}
