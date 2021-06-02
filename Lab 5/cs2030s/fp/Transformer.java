/**
 * The Transformer interface consist of an abstract
 * method takes in an argument of generic type T
 * and returns a value of generic type U.
 *
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author
 */

package cs2030s.fp;

public interface Transformer<T, U> {
  public U transform(T t);
}
