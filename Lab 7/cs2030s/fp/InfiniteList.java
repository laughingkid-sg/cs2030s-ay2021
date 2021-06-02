package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;


/**
 * A infinite list is a list with infinite number of elements
 * generated with lazy evaluation.
 *
 * @author 
 * @version CS2030S AY 20/21 Sem 2
 *
 */

public class InfiniteList<T> {
  /** The Maybe class encapsulated by Lazy.
   */
  private final Lazy<Maybe<T>> head;

  /** The InfiniteList class encapsulated by Lazy.
   */
  private final Lazy<InfiniteList<T>> tail;

  /** A static instance of EmptyList.
   */
  private static final EmptyList emptyList = new EmptyList();

  /**
   * A privte default constrcutor that takes in no element.
   *
   */
  InfiniteList() {
    head = null;
    tail = null;
  }

  /**
   * A private constructor to initialized the class if
   * type T and type InfintiteList Producer are used parameters.
   *
   * @param head The type T element to be stored as the head of the InfiniteList.
   * @param tail The Producer of InfiniteList to be stored as the tail of the InfiniteList.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * A private constructor to initialized the class if
   * lazy Maybe and lazy InfintiteList Producer are used as the parameters.
   *
   * @param head The type T element to be stored as the head of the InfiniteList.
   * @param tail The Producer of InfiniteList to be stored as the tail of the InfiniteList.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Returns an infinite sequential unordered InfiniteList where each element is generated
   * by the provided Producer. This is suitable for generating constant InfiniteList,
   * InfiniteList of random elements, etc
   *
   * @param producer The Producer of generated elements.
   * @param <T> The type of InfiniteList elements.
   * @return A new infinite sequential unordered InfiniteList.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())),
      Lazy.of(() -> InfiniteList.generate(producer)));
  }

  /**
   * Returns an infinite sequential ordered InfiniteList produced by iterative application
   * of a function next to an initial element seed, producing a Stream consisting of seed,
   * next(seed), next(next(seed)), etc.
   *
   * @param seed The initial element.
   * @param next A function to be applied to the previous element to produce a new element.
   * @param <T> The type of InfiniteList elements.
   * @return A new sequential InfiniteList.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(seed, () ->
      InfiniteList.iterate(next.transform(seed), next));
  }

  /**
   * Returns the head type T element of this InfiniteList, or the next type T if the head
   * of this InfiniteList is an instance of an empty Maybe.
   *
   * @return The first element of type T.
   */
  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * Returns the tail of InfiniteList, or the following InfiniteList if the tail of
   * this InfiniteStream is an instance of an empty Maybe.
   *
   * @return The first InfiniteList of type T.
   */
  public InfiniteList<T> tail() {
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Returns a InfiniteList consisting of the results of applying the
   * given function mapper to the elements of this InfiniteList.
   *
   * @param mapper A non-interfering, stateless function to apply to each element.
   * @param <R> The element type of the new InfiniteList.
   * @return The new InfiniteList.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    /*
    return new InfiniteList<>(
        Lazy.of(() -> this.head.get().map(x -> false).orElse(true)
          ? Maybe.none()
          : Maybe.some(mapper.transform(this.head()))),
        Lazy.of(() -> this.tail.get().map(mapper)));*/
    return new InfiniteList<R>(
        head.map(h -> h.map(mapper)),
        tail.map(t -> t.map(mapper)));
  }

  /**
   * Returns a InfiniteList consisting of the elements of this
   * InfiniteList that match the given predicate.
   *
   * @param predicate A non-interfering, stateless predicate to apply to
   *                  each element to determine if it should be included.
   * @return The new InfiniteList.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    /*
    return new InfiniteList<>(
      Lazy.of(() -> this.head.get().map(x -> false).orElse(true)
        ? Maybe.none()
        : predicate.test(this.head())
          ? Maybe.some(this.head())
          : Maybe.none()),
      Lazy.of(() -> this.tail.get().filter(predicate)));*/
    return new InfiniteList<T>(
        head.map(h -> h.filter(predicate)),
        tail.map(t -> t.filter(predicate))
        );
  }

  /**
   * Returns an empty sequential InfiniteList.
   *
   * @param <T> The type of stream elements.
   * @return An empty sequential InfiniteList.
   */
  public static <T> InfiniteList<T> empty() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> empty = (InfiniteList<T>) emptyList;
    return empty;
  }

  /**
   * Returns a InfiniteList consisting of the elements of this InfiniteList,
   * truncated to be no longer than n in length, the end of the list is marked
   * by an EmptyList.
   *
   * @param n The number of elements the InfiniteList should be limited to.
   * @return The new stream.
   */
  public InfiniteList<T> limit(long n) {
    return n <= 0
      ? empty()
      : new InfiniteList<T>(
            Lazy.of(() -> Maybe.some(this.head())),
            Lazy.of(() -> this.tail().limit(n - 1)));
  }

  /**
   * Returns a InfiniteList consisting of a subset of elements taken
   * from this InfiniteList that match the given predicate, the end of the list is
   * marked by an EmptyList.
   *
   * @param predicate A non-interfering, stateless predicate to apply to elements
   *                  to determine the longest prefix of elements.
   * @return The new InfiniteList.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Boolean> test = Lazy.of(() -> predicate.test(this.head()));
    return new InfiniteList<>(
        Lazy.of(() -> test.get() ? Maybe.some(this.head()) : Maybe.none()),
        Lazy.of(() -> test.get() ? this.tail().takeWhile(predicate) : empty()));
  }

  /**
   * If a value is not present, returns true, otherwise false (calling isEmpty()
   * on an instance of InfiniteList with 0 elements should return false because we
   * are testing if the caller is a special marker to terminate the list ).
   *
   * @return True if a EmptyList, otherwise false.
   */
  public boolean isEmpty() {
    return false;
  }

  /**
   * Performs a reduction on the elements of this InfiniteList,
   * using the provided identity and combining functions.
   *
   * @param identity The identity value for the combiner function.
   * @param accumulator An associative, non-interfering, stateless function for
   *                    combining two values.
   * @param <U> The type of the result.
   * @return The result of the reduction.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {

    U result = identity;
    InfiniteList<T> infiniteList = this;
    while (!infiniteList.isEmpty()) {
      if (infiniteList.head.get().map(x -> true).orElse(false)) {
        result = accumulator.combine(result, infiniteList.head());
      }
      infiniteList = infiniteList.tail.get();
    }
    return result;
    // return accumulator.combine(this.tail().reduce(identity, accumulator), this.head());
  }

  /**
   * Returns the count of elements in this InfiniteList.
   *
   * @return The count of elements in this InfiniteList.
   */
  public long count() {
    /*
    long counter = 0;
    InfiniteList<T> infiniteList = this;
    while (!infiniteList.isEmpty()) {
      if (infiniteList.head.get().map(x -> true).orElse(false)) {
        counter++;
      }
      infiniteList = infiniteList.tail.get();
    }
    return counter;*/
    return reduce(0L, (x, y) -> x + 1L);
  }

  /**
   * Returns a list that collects the elements in the InfiniteList into a List.
   *
   * @return A ArrayList which collects all the input elements into a List.
   */
  public List<T> toList() {
    List<T> thisList = new ArrayList<>();
    InfiniteList<T> infiniteList = this;
    while (!infiniteList.isEmpty()) {
      if (infiniteList.head.get().map(x -> true).orElse(false)) {
        thisList.add(infiniteList.head());
      }
      infiniteList = infiniteList.tail.get();
    }
    return thisList;
  }

  /**
   * Return the string representation of the value.
   *
   * @return The string representation of the value.
   */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  private static class EmptyList extends InfiniteList<Object> {
    EmptyList() {
      super();
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public Object head() {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() {
      return this;
    }

    @Override
    public boolean equals(Object obj) {
      return (obj instanceof EmptyList);
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return this.empty();
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return this;
    }

    @Override
    public InfiniteList<Object> limit(long n) {
      return this;
    }

    @Override
    public List<Object> toList() {
      return List.of();
    }

    @Override
    public String toString() {
      return "[" + "]";
    }

    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return this;
    }

    @Override
    public long count() {
      return 0;
    }

    /*
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return accumulator.combine(empty(), identity);
    }*/
  }
}
