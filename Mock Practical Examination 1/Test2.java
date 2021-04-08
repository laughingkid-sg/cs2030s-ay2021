class Test2 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expect("new Cab(\"SHA1234\", 5).toString()",
        new Cab("SHA1234", 3).toString(), "Cab SHA1234 (3 mins away)");

    i.expect("new Cab(\"SHA1234\", 1).toString()",
        new Cab("SHA1234", 1).toString(), "Cab SHA1234 (1 min away)");

    i.expect("new PrivateCar(\"SU4032\", 4).toString()",
        new PrivateCar("SU4032", 4).toString(), "PrivateCar SU4032 (4 mins away)");

    i.expect("new PrivateCar(\"SU4032\", 1).toString()",
        new PrivateCar("SU4032", 1).toString(), "PrivateCar SU4032 (1 min away)");
  }
}
