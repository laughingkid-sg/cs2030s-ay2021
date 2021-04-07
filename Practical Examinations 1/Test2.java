class Test2 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expect("Operation.of('*', new Operand(2), new Operand(3)).eval() is 6",
        Operation.of('*', new Operand(2), new Operand(3)).eval(), 6);

    i.expect("Operation.of('+', new Operand(\"hello\"), new Operand(\"world\")).eval()" + 
        " is \"helloworld\"",
        Operation.of('+', new Operand("hello"), new Operand("world")).eval(), "helloworld");

    i.expect("Operation.of('^', new Operand(true), new Operand(false)).eval() is true", 
        Operation.of('^', new Operand(true), new Operand(false)).eval(), true);

    i.expect("Operation.of('!', new Operand(2), new Operand(3)) is null", 
        Operation.of('!', new Operand(2), new Operand(3)), null);

    Operation o1 = Operation.of('*', new Operand(2), new Operand(3));
    i.expect("Operation.of('*',\n    Operation.of('*', new Operand(2), new Operand(3))" +
        ",\n    new Operand(4)\n  ).eval() is 24",
        Operation.of('*', o1, new Operand(4)).eval(), 24);

    Operation o2 = Operation.of('*', new Operand(2), new Operand(4));
    i.expect("Operation.of('*',\n    Operation.of('*', new Operand(2), new Operand(3))," +
        "\n    Operation.of('*', new Operand(2), new Operand(4))\n  ).eval() is 48",
        Operation.of('*', o1, o2).eval(), 48);

    boolean ok = false;
    String msg = "";
    try { 
      Operation.of('*', new Operand("1"), new Operand(3)).eval();
    } catch (Exception e) {
      ok = (e instanceof InvalidOperandException);
      msg = e.getMessage();
    }
    i.expect("\"1\" * 3: InvalidOperandException should thrown", 
        ok, true);
    i.expect("\"1\" * 3: Exception should contain the right message", 
        msg, "ERROR: Invalid operand for operator *");

    ok = false;
    msg = "";
    try { 
      Operation.of('+', new Operand(1), new Operand(4)).eval();
    } catch (InvalidOperandException e) {
      ok = (e instanceof InvalidOperandException);
      msg = e.getMessage();
    }
    i.expect("1 + 4: InvalidOperandException should thrown", ok, true);
    i.expect("1 + 4: Exception should contain the right message", 
        msg, "ERROR: Invalid operand for operator +");

    ok = false;
    msg = "";
    try { 
      Operation.of('^', new Operand(false), new Operand(3)).eval();
    } catch (InvalidOperandException e) {
      ok = (e instanceof InvalidOperandException);
      msg = e.getMessage();
    }
    i.expect("false ^ 3: InvalidOperandException should thrown", ok, true);
    i.expect("false ^ 3: Exception should contain the right message", 
        msg, "ERROR: Invalid operand for operator ^");

    ok = false;
    msg = "";
    try { 
      o1 = Operation.of('*', new Operand(1), new Operand(3));
      o2 = Operation.of('^', new Operand(false), new Operand(false));
      Operation.of('+', o1, o2).eval();
    } catch (InvalidOperandException e) {
      ok = (e instanceof InvalidOperandException);
      msg = e.getMessage();
    }
    i.expect("(1 * 3) + (false ^ false): InvalidOperandException should thrown", ok, true);
    i.expect("(1 * 3) + (false ^ false): Exception should contain the right message", 
        msg, "ERROR: Invalid operand for operator +");

    ok = false;
    msg = "";
    try { 
      o1 = Operation.of('*', new Operand(1), new Operand("3"));
      o2 = Operation.of('^', new Operand(false), new Operand(false));
      Operation.of('+', o1, o2).eval();
    } catch (InvalidOperandException e) {
      ok = (e instanceof InvalidOperandException);
      msg = e.getMessage();
    }
    i.expect("(1 * \"3\") + (false ^ false): InvalidOperandException should thrown", 
        ok, true);
    i.expect("(1 * \"3\") + (false ^ false): Exception should " +
        "contain the right message", msg, "ERROR: Invalid operand for operator *");
  }
}
