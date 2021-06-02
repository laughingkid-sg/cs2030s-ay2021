/**
 * The BooleanCondition is a generic interface with
 * a single abstract boolean method test.
 *
 * The method test take a single argument of type T.
 *
 *  CS2030S Lab 5
 *  AY20/21 Semester 2
 *
 * @author 
 */

package cs2030s.fp;

public interface BooleanCondition<T> {
  public boolean test(T t);
}
