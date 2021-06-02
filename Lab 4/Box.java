/**
 * The Box class is a wrapper class that can be used to
 * store an item of any reference type.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
class Box<T> {
  private final T value;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  /**
   * Constructor for a box.
   *
   * @param item The item to be the value of the box.
   */
  private Box(T item) {
    this.value = item;
  }

  public static <T> Box<T> of(T item) {
    if (item == null) {
      return null;
    } else {
      Box<T> myBox = new Box<T>(item);
      return myBox;
    }
  }

  public static <T> Box<T> ofNullable(T item) {
    if (item == null) {
      return Box.empty();
    } else {
      Box<T> myBox = new Box<T>(item);
      return myBox;
    }
  }

  public static <T> Box<T> empty() {
    @SuppressWarnings("unchecked")
    Box<T> myBox = (Box<T>) EMPTY_BOX;
    return myBox;
  }

  public boolean isPresent() {
    return !(this.value == null);
  }

  public Box<T> filter(BooleanCondition<? super T> b) {
    if (this.isPresent() && b.test(this.value)) {
      return this;
    } else {
      return Box.empty();
    }
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U> t) {
    if (this.isPresent()) {
      Box<U> myBox = new Box<U>(t.transform(this.value));
      return myBox;
    } else {
      return Box.empty();
    }
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Box) {
      @SuppressWarnings("unchecked")
      Box<T> myBox = (Box<T>) object;
      if (!(myBox.isPresent()) && !this.isPresent()) {
        return true;
      } else if (myBox.isPresent() && this.isPresent()) {
        return this.value.equals(myBox.value);
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    if (this.value == null) {
      return "[]";
    } else {
      return "[" + this.value + "]";
    }
  }
}
