class Request {

  private int distance;
  private int noOfPassengers;
  private int timeOfRequest;

  public Request(int distance, int noOfPassengers, int timeOfRequest) {
    this.distance = distance;
    this.noOfPassengers = noOfPassengers;
    this.timeOfRequest = timeOfRequest;
  }

  public int getDistance() {
    return this.distance;
  }

  public int getPassengers() {
    return this.noOfPassengers;
  }

  public int getTime() {
    return this.timeOfRequest;
  }
}
