class Test1 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expect("new JustRide().computeFare(new Request(20, 3, 1000))",
        new JustRide().computeFare(new Request(20, 3, 1000)),
        440);

    i.expect("new JustRide().computeFare(new Request(10, 1, 900))",
        new JustRide().computeFare(new Request(10, 1, 900)),
        720);

    i.expect("new TakeACab().computeFare(new Request(20, 3, 1000))",
        new TakeACab().computeFare(new Request(20, 3, 1000)),
        860);

    i.expect("new TakeACab().computeFare(new Request(10, 1, 900))",
        new TakeACab().computeFare(new Request(10, 1, 900)),
        530);

    i.expect("new ShareARide().computeFare(new Request(20, 3, 1000))",
        new ShareARide().computeFare(new Request(20, 3, 1000)),
        333);

    i.expect("new ShareARide().computeFare(new Request(10, 1, 900))",
        new ShareARide().computeFare(new Request(10, 1, 900)),
        1000);

    i.expect("new JustRide().toString()",
        new JustRide().toString(), "JustRide");

    i.expect("new TakeACab().toString()",
        new TakeACab().toString(), "TakeACab");

    i.expect("new ShareARide().toString()",
        new ShareARide().toString(), "ShareARide");
  }
}
