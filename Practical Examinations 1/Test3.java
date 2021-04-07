class Test3 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expect("The list AAA AA, A should have a length of 3",
        new Pair<>("AAA", new Pair<>("AA", new Pair<>("A", new EmptyList<>()))).length(), 
        3);

    i.expect("An empty list should have a length of 0",
        new EmptyList<>().length(),
        0);

    Object intList1 = new Pair<>(1, new Pair<>(2, new Pair<>(3, new EmptyList<>())));
    Object intList2 = new Pair<>(1, new Pair<>(2, new Pair<>(3, new EmptyList<>())));

    i.expect("A list should be equal to itself",
        intList1.equals(intList1), true);

    i.expect("The list 1 2 3 should be equal to another list of 1 2 3",
        intList1.equals(intList2), true);

    i.expect("The list 1 2 3 should not be equal to a list of string",
        intList1.equals(new Pair<>("1", new Pair<>("2", new Pair<>("3", 
                new EmptyList<>())))), false);


    i.expect("The list 1 2 3 should not be equal to an empty list",
        intList1.equals(new EmptyList<>()), false);

    i.expect("Two empty lists should be equal to each other",
        new EmptyList<Integer>().equals(new EmptyList<String>()), true);
  }
}
