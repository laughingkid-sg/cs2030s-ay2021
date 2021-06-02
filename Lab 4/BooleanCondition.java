/**
 * The BooleanCondition is a generic interface with
 * a single abstract boolean method test.
 *
 * The method test take a single argument of type T.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
interface BooleanCondition<T> {
  public boolean test(T t);
}
