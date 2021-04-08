import cs2030s.fp.Lazy;
import cs2030s.fp.Transformer;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper around an lazily evaluated list that
 * can be generated with a lambda expression.
 *
 * @author Goh Zheng Teck (B16J)
 * @version CS2030S AY 20/21 Sem 2
 *
 */
class LazyList<T> {
  /** The wrapped java.util.List object */
  private List<Lazy<T>> list;

  /** 
   * A private constructor to initialize the list to the given one. 
   *
   * @param list The given java.util.List to wrap around.
   */
  private LazyList(List<Lazy<T>> list) {
    this.list = list;
  }

  /** 
   * Generate the content of the list.  Given x and a lambda f, 
   * generate the list of n elements as [x, f(x), f(f(x)), f(f(f(x))), 
   * ... ]
   *
   * @param <T> The type of the elements in the list.
   * @param n The number of elements.
   * @param seed The first element.
   * @param f The transformation function on the elements.
   * @return The created list.
   */
  public static <T> LazyList<T> generate(int n, T seed, Transformer<T, T> f) {
    LazyList<T> lazyList = new LazyList<>(new ArrayList<>());
    Lazy<T> curr = Lazy.of(seed);
    for (int i = 0; i < n; i++) {
      lazyList.list.add(curr);
      curr = curr.map(f);
    }
    return lazyList;
  }

  /** 
   * Return the element at index i of the list.  
   *
   * @param i The index of the element to retrieved (0 for the 1st element).
   * @return The element at index i.
   */
  public T get(int i) {
    return this.list.get(i).get();
  }

  /** 
   * Find the index of a given element.
   *
   * @param v The value of the element to look for.
   * @return The index of the element in the list.  -1 is element is not in the list.
   */
  public int indexOf(T v) {
    return this.list.indexOf(Lazy.of(v));
  }

  /** 
   * Return the string representation of the list.
   *
   * @return The string representation of the list.
   */
  @Override
  public String toString() {
    return this.list.toString();
  }
}
