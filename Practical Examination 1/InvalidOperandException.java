/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */

class InvalidOperandException extends RuntimeException {
  public InvalidOperandException(char value) {
    super("ERROR: Invalid operand for operator " + value);
  }
}
