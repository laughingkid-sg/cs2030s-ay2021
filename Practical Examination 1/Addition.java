/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */
 
class Addition extends Operation {

  public Addition(Expression exp1, Expression exp2){
    super('+', exp1, exp2);
  }

	public String eval() {
		if (!(super.getExp1().eval() instanceof String) || !(super.getExp2().eval() instanceof String)) {
      throw new InvalidOperandException('+');
    }
    return ((String) super.getExp1().eval() + (String) super.getExp2().eval());
	}
}
