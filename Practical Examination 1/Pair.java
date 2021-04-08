/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */

public class Pair<T> implements SourceList<T> {
  private T first;
  private SourceList<T> second;

  public Pair(T first, SourceList<T> second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public T getFirst() {
    return this.first;
  }

  @Override
  public SourceList<T> getSecond() {
    return this.second;
  }

  @Override
  public String toString() {
    return this.first + ", " + this.second;
  }

  // Write your code here
  public int length() {
    return 1 + this.second.length();
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Pair) {
      @SuppressWarnings("unchecked")
      Pair<T> myList = (Pair<T>) object;
      if (myList.getFirst() == this.first) {
        return this.second.equals(myList.getSecond());
      }
    }
    return false;
  }

  public SourceList<T> filter(BooleanCondition<? super T> b) {
    if (b.test(this.first)) {
      return new Pair<T>(this.first, this.second.filter(b));
    } else {
      return this.second.filter(b);
    }
  }

  public <U> SourceList<U> map(Transformer<? super T, ? extends U> t) {
    return new Pair<U>(t.transform(this.first), this.second.map(t));
  }
}
