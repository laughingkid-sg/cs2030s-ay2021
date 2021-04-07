/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */

abstract class Operation implements Expression {
  private Expression expression1;
  private Expression expression2;
  private char op;

  public Operation(char op, Expression exp1, Expression exp2) {
    this.expression1 = exp1;
    this.expression2 = exp2;
    this.op = op;
  }

  public static Operation of(char op, Expression exp1, Expression exp2) {
    if (op == '*') {
      return new Multiply(exp1, exp2);
    } else if (op == '+') {
      return new Addition(exp1, exp2);
    } else if (op == '^') {
      return new  ExclusiveOR(exp1, exp2);
    } else {
      return null;
    }
  }

  public Expression getExp1() {
    return this.expression1;
  }

  public Expression getExp2() {
    return this.expression2;
  }

}
