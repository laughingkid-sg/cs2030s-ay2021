package cs2030s.fp;

import java.util.List;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  InfiniteList() { 
    head = null; 
    tail = null;
  }

  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    // TODO
    return new InfiniteList<>();
  }

  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    // TODO
    return new InfiniteList<>();
  }

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    // TODO
    this.head = null;
    this.tail = null;
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    // TODO
    this.head = null;
    this.tail = null;
  }

  public T head() {
    // TODO
    return null;
  }

  public InfiniteList<T> tail() {
    // TODO
    return new InfiniteList<>();
  }

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>();
  }

  public static <T> InfiniteList<T> empty() {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>();
  }

  public boolean isEmpty() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return null;
  }

  public long count() {
    // TODO
    return 0;
  }

  public List<T> toList() {
    // TODO
    return List.of();
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
