import cs2030s.fp.Consumer;
import cs2030s.fp.Transformer;
import cs2030s.fp.Try;
import java.io.IOException;

class Test4 {
  public static void main(String[] args) throws Throwable {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Try.success(4).onFailure(System.out::println).get()",
        () -> Try.success(4).onFailure(System.out::println).get(),
        4);

    i.expectException("Try.failure(new IOException()).onFailure(System.out::println).get()", 
        () -> Try.failure(new IOException()).onFailure(System.out::println).get(),
        new IOException());

    i.expectException("Try.failure(new IOException()).onFailure(e -> { int x = 1 / 0; }).get()", 
        () -> Try.failure(new IOException()).onFailure(e -> { 
          int x = 1 / 0;
        })
        .get(),
        new ArithmeticException());

    i.expectCompile("Consumer<Object> print = System.out::println;\n" +
        "Try.<Number>success(4).onFailure(print) compiles without error", 
        "cs2030s.fp.Consumer<Object> print = System.out::println; " +
        "cs2030s.fp.Try.<Number>success(4).onFailure(print);",
        true);

    i.expectReturn("Try.success(4).recover(e -> 10).get()",
        () -> Try.success(4).recover(e -> 10).get(), 
        4);

    i.expectReturn("Try.failure(new IOException()).recover(e -> 10).get()",
        () -> Try.failure(new IOException()).recover(e -> 10).get(),
        10);

    i.expectException("Try.failure(new IOException()).recover(e -> e.hashCode() / 0).get()", 
        () -> Try.failure(new IOException()).recover(e -> e.hashCode() / 0).get(),
        new ArithmeticException());

    i.expectCompile("Transformer<Object, Integer> hash = x -> x.hashCode();\n" +
        "Try.<Number>success(4).recover(hash); compiles without error", 
        "cs2030s.fp.Transformer<Object, Integer> hash = x -> x.hashCode();" +
        "cs2030s.fp.Try.<Number>success(4).recover(hash);",
        true);
  }
}
