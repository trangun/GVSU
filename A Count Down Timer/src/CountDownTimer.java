import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

/**********************************************************************
 * Create count down timer program for Grand Rapids city
 *
 * @author Trang Nguyen
 * @version Jan 19, 2018
 **********************************************************************/

public class CountDownTimer {

    /**
     * used for suspend method
     */
    private static boolean suspend = false;

    /**
     * Hold for number of hours
     */
    private int hours;

    /**
     * Hold for number of minutes
     */
    private int minutes;

    /**
     * Hold for number of seconds
     */
    private int seconds;

    /******************************************************************
     *  Default constructor that creates CountDownTimer
     *****************************************************************/
    public CountDownTimer() {
    }

    /******************************************************************
     * Constructor creates countDownTimer specified hours, minutes,
     * and seconds.
     * @param hours of the timer
     * @param minutes of the timer
     * @param seconds of the timer
     * @throws illegal argument exception
     *****************************************************************/
    public CountDownTimer(int hours, int minutes, int seconds) {
        if (minutes >= 60 || seconds >= 60 || hours < 0 || minutes < 0
                || seconds < 0)
            throw new IllegalArgumentException();

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /******************************************************************
     * Constructor creates count down timer specified minutes and
     * seconds.
     * @param minutes of the timer
     * @param seconds of the timer
     * @throws illegal argument exception
     *****************************************************************/
    public CountDownTimer(int minutes, int seconds) {
        if (minutes >= 60 || seconds >= 60 || minutes < 0
                || seconds < 0)
            throw new IllegalArgumentException();

        this.minutes = minutes;
        this.seconds = seconds;
        hours = 0;
    }

    /******************************************************************
     * Constructor creates CountDownTimer specified seconds.
     * @param seconds of the timer
     *****************************************************************/
    public CountDownTimer(int seconds) {
        if (seconds >= 60 || seconds < 0)
            throw new IllegalArgumentException();

        this.seconds = seconds;
        minutes = 0;
        hours = 0;
    }

    /******************************************************************
     * Constructor that initializes the instance variables with the
     * other CountDownTimer parameter.
     * @param other
     * @throws illegal argument exception
     *****************************************************************/
    public CountDownTimer(CountDownTimer other) {
        if (other == null)
            throw new IllegalArgumentException();

        this.hours = other.getHours();
        this.minutes = other.getMinutes();
        this.seconds = other.getSeconds();
    }

    /******************************************************************
     * Constructor that accepts a String as a parameter.
     * @param startTime - a string that fulfills the desired format of
     *                  timer.
     * @throws illegal argument exception
     *****************************************************************/
    public CountDownTimer(String startTime) {
        try {
            String[] parts = startTime.split(":");
            if (parts.length == 1) {
                seconds = Integer.parseInt(parts[0]);
            } else if (parts.length == 2) {
                minutes = Integer.parseInt(parts[0]);
                seconds = Integer.parseInt(parts[1]);
            } else if (parts.length == 3) {
                hours = Integer.parseInt(parts[0]);
                minutes = Integer.parseInt(parts[1]);
                seconds = Integer.parseInt(parts[2]);
            }

        } catch (Exception error) {
            throw new IllegalArgumentException();
        }

        if (minutes >= 60 || seconds >= 60 ||
                hours < 0 || minutes < 0 || seconds < 0)
            throw new IllegalArgumentException();
    }

    /******************************************************************
     * Static method that returns true if all of the objects in
     * CountDownTimer are the same.
     * @param t1 the first timer
     * @param t2 the second timer
     * @return boolean whether the objects of the timer are the same
     *****************************************************************/
    public static boolean equals(CountDownTimer t1, CountDownTimer t2){
        return (t1.hours == t2.hours && t1.minutes == t2.minutes
                && t1.seconds == t2.seconds);
    }

    /******************************************************************
     * Static method that has desired return value when comparing two
     * CountDownTimer objects.
     * @param t1 the first timer
     * @param t2 the second timer
     * @return int the desired value
     *****************************************************************/
    public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
        return t1.compareTo(t2);
    }

    /******************************************************************
     * Main method to test each method separately and completely.
     * @param args argument of the method
     * @throws illegal argument exception
     ******************************************************************/
    public static void main(String[] args) {

        CountDownTimer s = new CountDownTimer("2:59:8");
        System.out.println("Time: " + s);

        s = new CountDownTimer("2:5:8");
        System.out.println("Time: " + s);

        s = new CountDownTimer("2:0:0");
        System.out.println("Time: " + s);

        s = new CountDownTimer("20:8");
        System.out.println("Time: " + s);

        s = new CountDownTimer("2:8");
        System.out.println("Time: " + s);

        s = new CountDownTimer("1:0");
        System.out.println("Time: " + s);

        s = new CountDownTimer("10:10");
        System.out.println("Time: " + s);

        s = new CountDownTimer("12");
        System.out.println("Time: " + s);

        s = new CountDownTimer("8");
        System.out.println("Time: " + s);

        CountDownTimer s1 = new CountDownTimer(25, 2, 20);
        System.out.println("Time: " + s1);
        s1.sub(1000);
        System.out.println("Time: " + s1);
        s1.add(1000);
        System.out.println("Time: " + s1);

        CountDownTimer s2 = new CountDownTimer(40, 10, 20);
        s2.sub(100);

        for (int i = 0; i < 4000; i++)
            s2.dec();
        System.out.println("Time: " + s2);

        for (int i = 0; i < 4000; i++)
            s2.inc();
        System.out.println("Time: " + s2);

        s1.add(-200);
        System.out.println("Time: " + s1);

        s1.add(200);
        System.out.println("Time: " + s1);

        s1.add(s2);
        System.out.println("Time: " + s1);

        s2 = new CountDownTimer(100,2,3);
        s2.sub(s1);
        System.out.println("Time: " + s2);

        s = new CountDownTimer(2, 4);
        System.out.println("Time: " + s);

        s = new CountDownTimer(50);
        System.out.println("Time: " + s);

        s = new CountDownTimer(1, 2, 2);
        System.out.println("Time: " + s);

        try {
            //error
            s = new CountDownTimer(10,100,30);

        } catch (IllegalArgumentException e) {
            System.out.println("Wrong!");
        }

        try {
            //error
            s = new CountDownTimer(10,40, 100);

        } catch (IllegalArgumentException e) {
            System.out.println("Wrong!");
        }

        s.load("saveIT");
        s1.save("timer");
        s1.load("timer");

        s1 = new CountDownTimer (25,2,20);
        s2 = new CountDownTimer("25:2:20");

        if(s1.equals(s2))
            System.out.println("Equal!");

        if (s2.compareTo(s1) == 0)
            System.out.println("Equal");

        if (CountDownTimer.compareTo(s1,s2) == 0)
            System.out.println("Equal");

        s2 = new CountDownTimer("21:2:20");
        if(!s1.equals(s2)) System.out.println("Not Equal!");

        if (s2.compareTo(s1) < 0)
            System.out.println("Less!");

        if (s1.compareTo(s2) > 0)
            System.out.println("Greater!");

        if (CountDownTimer.compareTo(s2,s1) < 0)
            System.out.println("Less!");

        if (CountDownTimer.compareTo(s1,s2) > 0)
            System.out.println("Greater!");
    }

    /******************************************************************
     * A method that turns "on" and "off" any method in CountDownTimer.
     * @param flag boolean condition
     *****************************************************************/
    public static void suspend(boolean flag) {
        CountDownTimer.suspend = flag;
    }

    /******************************************************************
     * Static method that returns the suspend boolean condition
     * @return boolean suspend
     *****************************************************************/
    public static boolean isSuspend() {
        return suspend;
    }

    /******************************************************************
     * Override hashCode method that goes along with equals method.
     * @return int hash in Objects class
     *****************************************************************/
    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds);
    }

    /******************************************************************
     * Override method that returns true when the CountDownTimer object
     * is exactly the same as the other object.
     * @param other - object in Object
     * @return boolean - whether the two objects are the same
     ******************************************************************/
    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof CountDownTimer) {
            CountDownTimer temp = (CountDownTimer) other;
            return (this.hours == temp.hours && this.minutes ==
                    temp.minutes && this.seconds == temp.seconds);
        }
        return false;
    }

    /******************************************************************
     * A method that returns the desired value when compering
     * CountDownTimer object to the other CountDownTimer object.
     * @param other - object in CountDownTimer
     * @return int - desired value
     ******************************************************************/
    public int compareTo(CountDownTimer other) {
        int timeInSeconds = convertToSeconds();
        int otherTimer = other.getHours() * 3600 + other.getMinutes() *
                60 + other.getSeconds();
        if (timeInSeconds > otherTimer)
            return 1;
        else if (timeInSeconds < otherTimer)
            return -1;
        else
            return 0;
    }

    /******************************************************************
     * Help convert the timer to seconds
     ******************************************************************/
    private int convertToSeconds() {
        return this.hours*3600 + this.minutes*60 + this.seconds;
    }

    /******************************************************************
     * Help convert seconds to the hours, minutes, and seconds of the
     * timer.
     * @param timeInSeconds - seconds need to be converted
     ******************************************************************/
    private void convertToTimer (int timeInSeconds) {
        this.hours = timeInSeconds / 3600;
        this.minutes = timeInSeconds % 3600 / 60;
        this.seconds = timeInSeconds % 3600 % 60;
    }

    /******************************************************************
     * Method that subtracts the number of seconds from the timer
     * @param seconds - the number of seconds need to use
     * @throws illegal argument exception
     ******************************************************************/
    public void sub(int seconds) {
        if (isSuspend()) return;
        int timeInSeconds = convertToSeconds();
        timeInSeconds -= seconds;
        if (timeInSeconds < 0) throw new IllegalArgumentException();
        convertToTimer(timeInSeconds);
    }

    /******************************************************************
     * A method that subtracts other timer from the timer we have.
     * @param other - the other timer
     * @throws illegal argument exception
     ******************************************************************/
    public void sub(CountDownTimer other) {
        if (isSuspend()) return;
        int timeInSeconds = convertToSeconds();
        int timer = other.getHours() * 3600 + other.getMinutes() *
                60 + other.getSeconds();
        timeInSeconds -= timer;
        if (timeInSeconds < 0) throw new IllegalArgumentException();
        convertToTimer(timeInSeconds);
    }

    /******************************************************************
     * Decrements the timer by 1 second.
     * @throws illegal argument exception
     ******************************************************************/
    public void dec() {
        if (isSuspend()) return;
        int timeInSeconds = convertToSeconds();
        timeInSeconds--;
        if (timeInSeconds < 0) throw new IllegalArgumentException();
        convertToTimer(timeInSeconds);
    }

    /******************************************************************
     * Method that adds the number of seconds from the timer
     * @param seconds - number of seconds need to use
     * @throws illegal argument exception
     *****************************************************************/
    public void add(int seconds) {
        if (isSuspend()) return;
        int timeInSeconds = convertToSeconds();
        timeInSeconds += seconds;
        if (timeInSeconds < 0) throw new IllegalArgumentException();
        convertToTimer(timeInSeconds);
    }

    /******************************************************************
     * A method that add other timer to the timer we have.
     * @param other - the other timer
     * @throws illegal argument exception
     ******************************************************************/
    public void add(CountDownTimer other) {
        if (isSuspend()) return;
        int timeInSeconds = convertToSeconds();
        timeInSeconds += other.getHours() * 3600 + other.getMinutes()
                * 60 + other.getSeconds();
        if (timeInSeconds < 0) throw new IllegalArgumentException();
        convertToTimer(timeInSeconds);
    }

    /******************************************************************
     * Increments the timer by 1 second.
     * @throws illegal argument exception
     ******************************************************************/
    public void inc() {
        if (isSuspend()) return;
        int timeInSeconds = convertToSeconds();
        timeInSeconds++;
        convertToTimer(timeInSeconds);
    }

    /******************************************************************
     * Returns the desired format for the timer.
     * @return string that represents the desired format for the timer
     ******************************************************************/
    public String toString() {
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    /******************************************************************
     * Returns the hours of timer
     * @return int the hours of timer
     ******************************************************************/
    public int getHours() {
        return hours;
    }

    /******************************************************************
     * Sets the hours of the timer passed as parameter.
     * @param hours of the timer
     *****************************************************************/
    public void setHours(int hours) {
        if (isSuspend()) return;
        this.hours = hours;
    }

    /******************************************************************
     * Returns the minutes of the timer.
     * @return int the minutes of the timer
     *****************************************************************/
    public int getMinutes() {
        return minutes;
    }

    /******************************************************************
     * Sets the minutes of timer passed as parameter.
     * @param minutes of the timer
     ******************************************************************/
    public void setMinutes(int minutes) {
        if (isSuspend()) return;
        this.minutes = minutes;
    }

    /******************************************************************
     * Returns the seconds of the timer.
     * @return int seconds of the timer
     ******************************************************************/
    public int getSeconds() {
        return seconds;
    }

    /******************************************************************
     * Sets the seconds of the timer passed as parameter.
     * @param seconds of the timer
     ******************************************************************/
    public void setSeconds(int seconds) {
        if (isSuspend()) return;
        this.seconds = seconds;
    }

    /******************************************************************
     * Saves the timer to a file and use the parameter of filename for
     * the name of the file.
     * @param filename name of the file
     * @throws Illegal argument exception
     ******************************************************************/
    public void save (String filename) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter
                    (new FileWriter(filename)));
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }

        out.println(hours);
        out.println(minutes);
        out.println(seconds);
        out.close();
    }

    /******************************************************************
     * Load the timer object from a file, use a parameter filenname for
     * ther name of the file.
     * @param filename - name of the file
     * @throws illegal argument exception
     *****************************************************************/
    public void load (String filename){
        try{
            // open the data file
            Scanner fileReader = new Scanner(new File(filename));

            // read one int
            hours = fileReader.nextInt();
            minutes = fileReader.nextInt();
            seconds = fileReader.nextInt();

            System.out.println (hours);
            System.out.println (minutes);
            System.out.println (seconds);
        }

        // problem reading the file
        catch(Exception e){
            throw new IllegalArgumentException();
        }
    }
}
