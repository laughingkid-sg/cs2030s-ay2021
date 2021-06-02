/**
 * The LongerThan class implements BooleanCondition
 * on String that checks if a given string is longer than
 * a given limit.
 *
 * The test method should return true if it is longer;
 * return false otherwise.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
class LongerThan implements BooleanCondition<String> {

  int input;

  public LongerThan(int input) {
    this.input = input;
  }

  public boolean test(String t) {
    return t.length() > this.input;
  }
}
