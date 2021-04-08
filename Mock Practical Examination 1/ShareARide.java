class ShareARide implements Service {

  private final int centsPerKM = 50;
  private final int surcharage = 500;

  public int computeFare(Request request) {
    int remainder = (request.getDistance() * centsPerKM) %
      request.getPassengers();

    if (request.getTime() >= 600 && request.getTime() <= 900) {
    return (request.getDistance() * this.centsPerKM - remainder) /
      request.getPassengers() + this.surcharage;
    }

    return (request.getDistance() * this.centsPerKM - remainder) /
      request.getPassengers();
  }

  public String toString() {
    return "ShareARide";
  }
}
