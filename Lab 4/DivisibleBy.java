/**
 * The DivisibleBy class implements BooleanCondition on
 * Integer that checks if a given integer is
 * divisible by another integer.
 *
 * The test method should return true if it is divisible;
 * return false otherwise.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
class DivisibleBy implements BooleanCondition<Integer> {

  int input;

  public DivisibleBy(int input) {
    this.input = input;
  }

  public boolean test(Integer t) {
    return t % this.input == 0;
  }
}
