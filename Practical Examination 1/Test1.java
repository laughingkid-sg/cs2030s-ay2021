class Test1 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expect("new Operand(5).eval()",
        new Operand(5).eval(), 5);
    i.expect("new Operand(\"string\").eval()",
        new Operand("string").eval(), "string");
    i.expect("new Operand(true).eval()", 
        new Operand(true).eval(), true);
    i.expect("InvalidOperandException('!').getMessage()",
        new InvalidOperandException('!').getMessage(), "ERROR: Invalid operand for operator !");
    i.expect("InvalidOperandException('+').getMessage()",
        new InvalidOperandException('+').getMessage(), "ERROR: Invalid operand for operator +");
  }
}
