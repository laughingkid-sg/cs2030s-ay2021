package cs2030s.fp;

/**
 * A lazy class encapsulate 
 * can be generated with a lambda expression.
 *
 * @author Goh Zheng Teck (B16J)
 * @version CS2030S AY 20/21 Sem 2
 *
 */ 
 
public class Lazy<T> {
  /** The Producer Interface.
   */
  private Producer<T> producer;

  /** The Maybe Class.
   */
  private Maybe<T> value;
  
  /**
   * A private constructor to initialize the class if 
   * a object is the parameter.
   *
   * @param item The item to be encapsulated. 
   */
  private Lazy(T item) {
    this.value = Maybe.some(item);
    this.producer = () -> item; 
  }

  /** 
   * A private constructor to initialize the class if
   * a Producer is the paramter. 
   *
   * @param p The Producer to be encapsulated.
   * */
  private Lazy(Producer<T> p) {
    this.producer = p;
    this.value = Maybe.none();
  }
  
  /**
   * Factory method to initializes the Lazy 
   * object with the given value.
   *
   * @param t The value to be encapsulated.
   * @param <T> The type of the value.
   * @return A new Lazy instance encapsulating given item.
   */
  public static <T> Lazy<T> of(T t) {
    return new Lazy<T>(t); 
  }

  /**
   * Factory method that takes in a producer that 
   * produces the value when needed.
   *
   * @param p The Producer to be encapsulated.
   * @param <T> The type of the value.
   * @return A new Lazy instance encapsulating given Producer.
   */
  public static <T> Lazy<T> of(Producer<T> p) {
    return new Lazy<T>(p);
  }

  /**
   * The get() method that is called when the value is needed.
   * If the value is already available, return that
   * value; otherwise, it will be computed.
   *
   * @return The value encapsulated in Lazy. 
   */
  public T get() { 
    this.value = Maybe.some(this.value.orElseGet(() -> this.producer.produce()));
    return this.value.orElseGet(this.producer);
  }

  /**
   * Return the string representation of the value.
   *
   * @return The string representation of the value;
   */
  @Override
  public String toString() {
    if (Maybe.none().equals(this.value)) {
      return "?";
    } else {
      return String.valueOf(this.get());
    }
  }

  /**
   * Returns true if the value of the Lazy are equal to 
   * each other and false otherwise. Equal an eager operation 
   * that causes the values to be evaluated (if not already cached).
   *
   *
   * @param o An Lazy to be compared with this item value for equality.
   * @return true if the arguments are equal to each other and false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Lazy) {
      Lazy<?> lazy = (Lazy<?>) o;
      return this.get().equals(lazy.get());
    } else {
      return false;
    }
  }

  /**
   * If a value is present, and the value matches the given BooleanCondition,
   * returns an Lazy containing the value, otherwise returns an empty
   * Lazy.
   *
   * @param b The predicate to apply to a value, if present.
   * @return A Lazy containing the value of this Lazy, if a value is
   *        present and the value matches the given predicate, otherwise an
   *        empty Lazy.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> b) {
    return Lazy.of(() -> b.test(this.get()));
  }

  /**
   * If a value is present, returns an Lazy encapsulating the result of
   * applying the given mapping function to the value in this lazy, 
   * otherwise returns an empty Lazy.
   *
   * @param t The mapping function to apply to a value, if present.
   * @param <U> The type of the value returned from the mapping function.
   * @return A Lazy encapsulating the result of applying a mapping function 
   *        to the value of this Lazy's value, if a value is present, otherwise
   *        an empty Lazy.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> t) {
    return Lazy.of(() -> t.transform(this.get()));
  }

  /**
   * If a value is present, returns the result of applying the given Lazy
   * bearing mapping function to the value, otherwise returns an empty
   * Lazy. This method is similar to map(Function), but the mapping function
   * is one whose result is already an Lazy, and if invoked, flatMap does
   * not wrap it within an additional Lazy.
   *
   * @param t The mapping function to apply to a value, if present.
   * @param <U> The type of the value returned from the mapping function.
   * @return The result of applying an Lazy bearing mapping function 
   *        to the value of this Lazy, if a value is present, otherwise an
   *        empty Lazy.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> t) {
    return Lazy.of(() -> t.transform(this.get()).get());
  }
  
  /**
   * The combine method takes in another Lazy object 
   * and a Combiner implementation to lazily combine 
   * the two Lazy objects (which may contain
   * values of different types) and return a new Lazy object.
   *
   * @param s The second value encapsulated by Lazy to be combined.
   * @param <R> The type of the value returned from combine function.
   * @param <S> The tpye of the second value for the combine function.
   * @param f The combining function to apply to the values, if present.
   * @return The result of applying the combiner f to this lazy
   *        value and given s encapsulated in a Lazy.
   */
  public <R, S> Lazy<R> combine(Lazy<S> s, Combiner<? super T, ? super S, ? extends R> f) {
    return Lazy.of(() -> f.combine(this.get(), s.get()));
  }
}

