public class TakeACab implements Service {

  private final int centsPerKM = 33;
  private final int bookingFee = 200;

  public int computeFare(Request request) {
    return centsPerKM * request.getDistance() + bookingFee;
  }

  public String toString() {
    return "TakeACab";
  }
}
