/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */
public class EmptyList<T> implements SourceList<T> {

  @Override
  public T getFirst() {
    return null;
  }

  @Override
  public SourceList<T> getSecond() {
    return this;
  }

  @Override
  public String toString() {
    return "EmptyList";
  }

  // Write your code here
  public int length() {
    return 0;
  }

  @Override
  public boolean equals(Object object) {
    return (object instanceof EmptyList);
  }

  public SourceList<T> filter(BooleanCondition<? super T> b) {
    return this;
  }

  public <U> SourceList<U> map(Transformer<? super T, ? extends U> t) {
    return new EmptyList<U>();
  }
}
