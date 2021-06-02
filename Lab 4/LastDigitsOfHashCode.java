/**
 * The LastDigitsOfHashCode class transform
 * the content of the box into a box of integer,
 * the value of which is the last k digits of
 * the value returned by calling hashCode() on
 * the content of the original box
 * (ignoring the positive/negative sign and leading zeros).
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
class LastDigitsOfHashCode implements Transformer<Object, Integer> {

  int input;

  /**
   * Constructor for a LastDigitsOfHashCode.
   *
   * @param input The last numbers of digits of the value return
   * calling hashcode.
   */
  public LastDigitsOfHashCode(int input) {
    this.input = input;
  }

  public Integer transform(Object o) {
    return (int) (Math.abs(o.hashCode()) % Math.pow(10, this.input));
  }
}
