package cs2030s.fp;

import java.lang.Throwable;

/*
 * CS2030S PE2 Question 1
 * AY20/21 Semester 2
 *
 * @author A
 *
 *
 */

public abstract class Try<T> {

  protected Try() {
  }

  public static <U> Try<U> of(Producer<? extends U> p) {
    try {
      return success(p.produce());
    } catch (Throwable t) {
      return failure(t);
    }
  }

  public static <T> Try<T> success(T input) {
    return new Success<>(input);
  }

  public static <T> Try<T> failure(Throwable e) {
    return new Failure<>(e);
  }

  public abstract T get() throws Throwable;

  public abstract <U> Try<U> map(Transformer<? super T, ? extends U> t);

  public abstract <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> t);

  public abstract Try<T> onFailure(Consumer<? super Throwable> c);

  public abstract Try<T> recover(Transformer<? super Throwable, ? extends T> t);

  public static class Success<T> extends Try<T> {
  
    private T value;

    public Success(T input) {
      this.value = input;
    }
    
    @Override
    public T get() throws Throwable {
      return this.value;
    }
    
    @Override
    public <U> Try<U> map(Transformer<? super T, ? extends U> t) {
      return Try.of(() -> t.transform(this.value));
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> t) {
      return Try.of(() -> t.transform(this.value).get());
    }

    @Override
    public Try<T> onFailure(Consumer<? super Throwable> c) {
      return this;
    }

    @Override
    public Try<T> recover(Transformer<? super Throwable, ? extends T> t) {
      return this;
    }
    
    @Override
    public boolean equals(Object o) {
      try {
        if (o instanceof Success) {
          Success<?> success = (Success<?>) o;
          return this.value == success.get();
        }
      } catch (Throwable e) {
        return false;
      }
      return false;
    }
  }

  public static class Failure<T> extends Try<T> {

    private final Throwable e;

    public Failure(Throwable e) {
      this.e = e;
    }
    
    @Override
    public T get() throws Throwable {
      throw e;
    }
    
    @Override
    public <U> Try<U> map(Transformer<? super T, ? extends U> t) {
      return new Failure<>(this.e); 
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> t) {
      return new Failure<>(this.e); 
    }
    
    @Override
    public Try<T> onFailure(Consumer<? super  Throwable> c) {
      try {
        c.consume(this.e);
        return this;
      } catch (Throwable e) {
        return new Failure<>(e);
      }
    }

    @Override
    public Try<T> recover(Transformer<? super Throwable, ? extends T> t) {
      return Try.of(() -> t.transform(this.e));
    }
    
    @Override
    public boolean equals(Object o) {
      if (o instanceof Failure) {
        Failure<?> failure = (Failure<?>) o;
        try {
          return this.e.toString() == failure.get().toString();
        } catch (Throwable e) {
          return e.toString() == this.e.toString();
        }
      }
      return false;
    }
  }
}
