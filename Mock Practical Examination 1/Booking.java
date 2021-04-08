class Booking implements Comparable<Booking> {
  private Car car; // 2 car type Cab & PrivateHire
  private Service service; // JustRide, TakeACab, ShareMeRide
  private Request request;

  public Booking(Car car, Service service, Request request) {   	
		if ((car.getType() == "PrivateCar" && service.toString() == "TakeACab") ||
       (car.getType() == "Cab" && service.toString() == "ShareARide")) {
			throw new IllegalArgumentException(car.toString() + " does not provide the "
         + service.toString() + " service.");
		}
		
	  this.car = car;
    this.service = service;
    this.request = request;
  }

  public int getFare() {
    return this.service.computeFare(this.request);
  }

  public int getTime() {
    return this.car.getTime();
  }

  @Override
  public int compareTo(Booking myBooking) {
		int fare = Integer.compare(this.getFare(), myBooking.getFare());
    if (fare == 0) {
			int time = Integer.compare(this.getTime(), myBooking.getTime());
      if (time == 0) {
      	return 1;
      } else {
				return time;
      }
    } else {
      return fare;
    }    
  }
}

