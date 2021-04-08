abstract class Car {
 
  private String type;
  private String licensePlate;
  private int timeArrival; 

  public Car(String licensePlate, int timeArrival, String type) {
    this.type = type;
    this.licensePlate = licensePlate;
    this.timeArrival = timeArrival;
  }

  public String getType() {
    return this.type;
  }

  public int getTime() {
    return this.timeArrival;
  }
  public String getPlate() {
    return this.licensePlate;
  }

  @Override
  public String toString() {
    return this.timeArrival == 1
      ? this.type + " " + this.licensePlate + " (" + this.timeArrival + " min away)" 
      : this.type + " " + this.licensePlate + " (" + this.timeArrival + " mins away)" ;
  }
}

