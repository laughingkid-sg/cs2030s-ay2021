/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */

class ExclusiveOR extends Operation {

  public ExclusiveOR(Expression exp1, Expression exp2){
    super('^', exp1, exp2);
  }

	public Boolean eval() {
		if (!(super.getExp1().eval() instanceof Boolean) || !(super.getExp2().eval() instanceof Boolean)) {
      throw new InvalidOperandException('^');
    }
    return ((Boolean) super.getExp1().eval() ^ (Boolean) super.getExp2().eval());
	}
}
