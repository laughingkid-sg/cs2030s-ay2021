package cs2030s.fp;

@FunctionalInterface
public interface Consumer<T> {

  void consume(T t);
}
