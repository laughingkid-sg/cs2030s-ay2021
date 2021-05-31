/**
 * This class encapsulates a Customer.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
public class Customer {
  private int id;
  private double arrival;
  private double serviceTime;

  /**
   * Method used to create an arriving Customer tagged with a
   * customer ID and arrival time.
   *
   * @param id Customer ID
   * @param arrival Customer Arrival Time
   */

  public Customer(int id, double arrival, double serviceTime) {
    this.id = id;
    this.arrival = arrival;
    this.serviceTime = serviceTime;
  }

  public int getId() {
    return this.id;
  }

  public double getArrival() {
    return this.arrival;
  }

  public double getService() {
    return this.serviceTime;
  }
}
