/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */

class Multiply extends Operation {
  public Multiply(Expression exp1, Expression exp2) {
    super('*', exp1, exp2);
  }

  public Integer eval() {
    if (!(super.getExp1().eval() instanceof Integer) || !(super.getExp2().eval() instanceof Integer)) {
      throw new InvalidOperandException('*');
    } else {
      return ((Integer) super.getExp1().eval() * (Integer) super.getExp2().eval());
    }
  }
}
