class JustRide implements Service {

  private final int centsPerKM = 22;
  private final int surCharage = 500;

  public int computeFare(Request request) {
    if (request.getTime() >= 600 &&  request.getTime() <= 900) {
      return this.centsPerKM * request.getDistance() + this.surCharage;
    } else {
      return this.centsPerKM * request.getDistance();
    }
  }

  public String toString(){
    return "JustRide";
  }
}
