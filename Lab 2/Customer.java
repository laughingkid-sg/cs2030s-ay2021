/**
 * This class encapsulates a Customer.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class Customer {
  private int id;
  private double arrivalTime;
  private double serviceTime;
  private double serviceBeginTime;
  private double departureTime;

  /**
   * Method used to create an arriving Customer tagged with a
   * customer ID and arrival time.
   *
   * @param id The customer ID.
   * @param arrivalTime The arrival time of the customer.
   * @param serviceTime The amount of time servicing the
   *                    customer.
   * @param serviceBeginTime The start time when the customer
   *                         is serviced.
   * @param departureTime The time when the customer departed.
   */

  public Customer(int id, double arrivalTime, double serviceTime,
      double serviceBeginTime, double departureTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.serviceBeginTime = serviceBeginTime;
    this.departureTime = departureTime;
  }

  public int getId() {
    return this.id;
  }

  public double getArrival() {
    return this.arrivalTime;
  }

  public double getService() {
    return this.serviceTime;
  }

  public double getBegin() {
    return this.serviceBeginTime;
  }

  public double getDeparture() {
    return this.departureTime;
  }

  public void updateServiceBeginTime(double time) {
    this.serviceBeginTime = time;
  }

  public void updateDepartureTime(double time) {
    this.departureTime = time;
  }

  @Override
  public String toString() {
    return "C" + String.format("%d", this.id);
  }
}
