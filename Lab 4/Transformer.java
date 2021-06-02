/**
 * The Transformer interface consist of an abstract
 * method takes in an argument of generic type T
 * and returns a value of generic type U.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
interface Transformer<T, U> {
  public U transform(T t);
}
