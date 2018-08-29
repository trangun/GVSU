import java.util.*;
import java.text.*;
/**
 * Create the Market Place for the simulation
 *
 * @author Trang Nguyen
 * @version November 2017
 */
public class MarketPlace
{
    /** Average time between customer arrivals*/
    private double avgArrivalTime;

    /** Average cashier service time */
    private double avgServiceTime;

    /** number of cashiers */
    private int numCashiers;

    /** whether the checkout area should be displayed */
    private boolean showCheckout;

    /** current time of the simuluation */
    private double currentTime;

    /** length of the longest line */
    private int maxLength;

    /** time at the longest line */
    private double timeMax;

    /** customer waiting time */
    private double waitTime;

    /** number of customer served */
    private int customerServed;

    /** ArrayList to keep track of customers waiting in line */
    private ArrayList <Customer> numCustomers;

    /** array represent the cashiers */
    private Customer [] cashier;

    /** maintain the list of future events */
    PriorityQueue <GVevent> futureEvents;

    /** randome variable to generate random arrival and random service times */
    private GVrandom r;

    /** contains results */
    private String results;

    /** Open time of the simuluation */
    private final int OPEN = 600;

    /** close time of the simuluation */
    private final int CLOSE = 1080;

    /**
     * Default Constructor
     */
    public MarketPlace () {
        reset();
        setParameters(3, 6.6, 2.5, false);
    }

    /**
     * returns the number of cashiers
     * @return int number of cashiers
     */
    public int getNumCashiers() {
        return numCashiers;
    }

    /**
     * returns the average customer arrival time
     * @return double average customer arrival time
     */
    public double getArrivalTime () {
        return avgArrivalTime;
    }

    /**
     * returns the average cashier service time
     * @return double average cashier service time
     */
    public double getServiceTime () {
        return avgServiceTime;
    }

    /**
     * returns the number of customers served during the most recent simulation
     * @return int number of customers served during the most recent simulation
     */
    public int getNumCustomersServed() {
        return customerServed;
    }

    /**
     * returns the appended results as a report
     * @return String results
     */
    public String getReport () {
        return results;
    }

    /**
     * returns the length of the longest line during the simulation
     * @return int length of the longest line during the simulation
     */
    public int getLongestLineLength () {
        return maxLength;
    }

    /**
     * returns the average wait time of all customers during the simulation
     * @return double average wait time of all customers during the simulation
     */
    public double getAverageWaitTime () {
        return waitTime/customerServed;
    }

    /**
     * set the instance variables passes as parameter
     * @param int number of cashiers
     * @param double average service time
     * @param double average arrival time
     * @param boolean check out area
     */
    public void setParameters (int num, double s, double a, boolean ck) {
        numCashiers = num;
        avgServiceTime = s;
        avgArrivalTime = a;
        showCheckout = ck;
        cashier = new Customer [numCashiers];
    }
    
    /**
     * change the length of the longest line to the int passes as parameter
     * @param int length of the longest line
     */
    public void setLongestLineLength(int num) {
        maxLength = num;
    }

    /**
     * simulates a customer getting in line
     */
    public void customerGetsInLine () {
        Customer newCustomers = new Customer(currentTime);
        numCustomers.add(newCustomers);
        if (getLongestLineLength() < numCustomers.size()) {
            setLongestLineLength(numCustomers.size());
            timeMax = currentTime;
        }

        int cashierAvailable = cashierAvailable();
        if (cashierAvailable != -1 && numCustomers.size() >= 0) {
            customerToCashier(cashierAvailable);
        }

        if (currentTime < CLOSE) {
            futureEvents.add(new GVarrival (this, randomFutureTime(getArrivalTime())));
        }
    }

    /**
     * simulates a customer completing a transaction with the cashier and leaving the building
     * @param int cashier number
     */
    public void customerPays (int num) {
        if (numCustomers.size() > 0) {
            customerToCashier(num);
        } else {
            cashier[num] = null;
        }
    }

    /**
     * resets all instance variables 
     */
    private void reset () {
        currentTime = 0.0;
        maxLength = 0;
        waitTime = 0.0;
        customerServed = 0;
        numCustomers = new ArrayList <Customer> ();
        futureEvents = new PriorityQueue <GVevent> ();
        r = new GVrandom();
        results = "";
    }

    /**
     * returns an available index in the cashier array
     * @return int cashier index
     */
    private int cashierAvailable() {
        for (int i = 0; i <= cashier.length - 1; i++) {
            if (cashier [i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * return a future time by adding the current simulation time to a random number
     * @param double random number
     */
    private double randomFutureTime(double avg) {   
        return currentTime + r.nextPoisson(avg);  
    }

    /**
     * moves a customer from the front of the line to an available cashier
     */
    private void customerToCashier (int num) {
        Customer c = numCustomers.remove(0);
        cashier[num] = c;

        customerServed++;
        double customerWaitTime = currentTime - c.getArrivalTime();
        waitTime += customerWaitTime;

        double futureTime = randomFutureTime(avgServiceTime);
        futureEvents.add(new GVdeparture (this, futureTime, num));

    }

    /**
     * controls the simulation from beginning to end
     */
    public void startSimulation () {
        reset();
        futureEvents.add(new GVarrival(this, OPEN));

        while(!futureEvents.isEmpty()){
            GVevent e = futureEvents.poll();
            currentTime = e.getTime();
            e.process();

            if (showCheckout) {
                showCheckoutArea();
             }
        }
        
        createReport();
    }

    /**
     * show checkout area that has current time, cashiers, and customers in line
     */
    public void showCheckoutArea () {
        String time = formatTime(currentTime) + " ";
        String customers = "";
        String cashiers = "";

        //represents number of waiting customers
        for (int i = 1; i < numCustomers.size(); i++) {
            customers += "*";
        }
        
        //represents the cashiers
        for (int i = 0; i < cashier.length; i++) {
            if (cashier [i] == null) {
                cashiers += "_";
            } else {
                cashiers += "C";
            }
        }
        
        cashiers += " ";
        results += "\n" + time + cashiers + customers + "\n";
    }

    /**
     * Summary report for the simulation
     */
    public void createReport () {
        DecimalFormat fmt1 = new DecimalFormat ("#");
        results += "\nSIMULATION PARAMETERS" + "\nNumber of cashiers: " + numCashiers
        + "\nAverage arrival: " + avgArrivalTime + "\nAverage service: " + avgServiceTime + 
        "\n\nRESULTS" +"\nAverage wait time: " + fmt1.format(getAverageWaitTime()) + " mins" + 
        "\nMax line length: " + maxLength + " at " + formatTime(timeMax) +
        "\nCustomers served: " + customerServed + "\nLast departure: " + formatTime(currentTime) + "\n";
    }

    /**
     * returns provided time
     * @return String provided time
     */
    public String formatTime (double mins) {
        String suffix;
        DecimalFormat fmtHour = new DecimalFormat ("0");
        DecimalFormat fmtMins = new DecimalFormat ("00");

        int hour = (int) mins / 60;
        int min = (int) mins % 60;

        if (mins < 720) {
            suffix = "am";
            if (hour == 0) {
                hour = 12;
            }
        } else {
            suffix = "pm";
            if (hour != 12) {
                hour -= 12;
            }
        }
        
        return fmtHour.format(hour) + ":" + fmtMins.format(min) + suffix;
    }
}
