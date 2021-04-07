/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author Boyd Anderson
 */
public class GreaterThanTwo implements BooleanCondition<Integer> {
  @Override
  public boolean test(Integer t) {
    return t > 2;
  }
}
