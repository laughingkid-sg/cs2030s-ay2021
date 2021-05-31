import cs2030s.fp.Try;
import java.io.IOException;

class Test3 {
  public static void main(String[] args) throws Throwable {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Try.success(4).flatMap(x -> Try.success(x + 1)).get()",
        () -> Try.success(4).flatMap(x -> Try.success(x + 1)).get(), 
        5);

    i.expectException("Try.success(4).flatMap(x -> Try.failure(new IOException()))",
        () -> Try.success(4).flatMap(x -> Try.failure(new IOException())).get(),
        new IOException());

    i.expectException("Try.failure(new NullPointerException())" +
        ".flatMap(x -> Try.success(x.toString()))",
        () -> Try.failure(new NullPointerException())
            .flatMap(x -> Try.success(x.toString())).get(),
        new NullPointerException());

    i.expectReturn("Try.failure(new IOException()).flatMap(x -> Try.success(x.toString()))" +
        ".equals(Try.failure(new IOException()));",
        () -> Try.failure(new IOException())
            .flatMap(x -> Try.success(x.toString()))
            .equals(Try.failure(new IOException())),
        true);

    i.expectException("Try.failure(new NullPointerException())\n" +
        "    .flatMap(x -> Try.failure(new IOException()));",
        () -> Try.<Integer>failure(new NullPointerException())
            .flatMap(x -> Try.failure(new IOException())).get(),
        new NullPointerException());
    
    i.expectCompile(
        "Transformer<Object, Try<Integer>> " +
        "hash = x -> Try.success(x.hashCode());\n" +
        "Try<Number> t = Try.success(\"hello\").flatMap(hash); compiles without error",
        "cs2030s.fp.Transformer<Object, cs2030s.fp.Try<Integer>> " +
        "hash = x -> cs2030s.fp.Try.success(x.hashCode());" +
        "cs2030s.fp.Try<Number> t = cs2030s.fp.Try.success(\"hello\").flatMap(hash);",
        true);
  }
}
