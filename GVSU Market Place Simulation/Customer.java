
/**
 * Write a description of class Customer here.
 *
 * @author Trang Nguyen
 * @version November 2017
 */
public class Customer
{
    /** time the customer arrived in line */
    private double time;
    
    /**
     * Constructor with parameter for class Customer
     * @param double - time the customer arrived in line
     */
    public Customer (double time) {
        this.time = time;
    }

    /**
     * Change the time to the double passes as parameter
     * @param  double time
     */
    public void setArrivalTime (double t) {
        // put your code here
        time = t;
    }
    
    /**
     * returns the arrival time
     * @return double time
     */
    public double getArrivalTime () {
        return time;
    }

    /**
     * main method to test Customer class
     */
    public static void main (String[] args) {
        Customer c1 = new Customer (2.30);
        System.out.println("" + c1);
    }
}
