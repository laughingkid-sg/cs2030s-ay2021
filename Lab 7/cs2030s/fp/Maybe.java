/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author 
 */

package cs2030s.fp;

public abstract class Maybe<T> {
  private static final None NONE = new None();

  protected abstract T get();

  public abstract <U extends T> T orElse(U value);

  public abstract <U extends T> T orElseGet(Producer<U> p);

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> none = (Maybe<T>) NONE;
    return none;
  }

  public static <T> Maybe<T> some(T item) {
    return new Some<T>(item);
  }

  public static <T> Maybe<T> of(T item) {
    if (item == null) {
      return Maybe.none();
    } else {
      return Maybe.some(item);
    }
  }


  public Maybe<T> filter(BooleanCondition<? super T> b) {
    if (this.equals(Maybe.none())) {
      return this;
    } else if ((this.get() != null) && !(b.test(this.get()))) {
      return Maybe.of(null);
    } else {
      return this;
    }
  }

  public <U> Maybe<U> map(Transformer<? super T, ? extends U> t) {
    if (this.equals(Maybe.none())) {
      return Maybe.none();
    } else {
      return Maybe.some(t.transform(this.get()));
    }
  }

  public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) {
    if (this.equals(Maybe.none())) {
      return Maybe.none();
    } else {
      @SuppressWarnings("unchecked")
      Maybe<U> out = (Maybe<U>) t.transform(this.get());
      return out;
    }
  }


  static final class Some<T> extends Maybe<T> {

    private T value;

    public Some(T item) {
      this.value = item;
    }

    @Override
    protected T get() {
      return this.value;
    }

    @Override
    public <U extends T> T orElse(U value) {
      return this.value;
    }

    @Override
    public <U extends T> T orElseGet(Producer<U> p) {
      return this.value;
    }

    @Override
    public String toString() {
      return "[" + this.value + "]";
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Some) {
        Some<?> maybe = (Some<?>) obj;
        if ((maybe.get() == null) && (this.value == null)) {
          return true;
        } else if ((this.value == null) && (maybe.get() != null)) {
          return false;
        } else {
          return this.value.equals(maybe.get());
        }
      } else {
        return false;
      }
    }
  }

  static final class None extends Maybe<Object> {

    @Override
    protected Object get() {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public Object orElse(Object value) {
      return value;
    }

    @Override
    public <U> U orElseGet(Producer<U> p) {
      return p.produce();
    }

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      return (obj instanceof None);
    }
  }
}
