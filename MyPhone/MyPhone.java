import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Random;
/**
 * Write a description of class MyPhone here.
 *
 * @author Trang Nguyen
 * @version October 2017
 */
public class MyPhone {

    /** number of texts */
    private int numTexts;

    /** amount of data consumed (in megabytes) */
    private double dataUsage;

    /** remaining battery life */
    private double batteryLife;

    /** customer name */
    private String name;

    /** ten digit phone number */
    private String phoneNumber;

    /** audio usage per minute */
    private final double DATA_PER_MIN = 65/60.0;

    /** the maximum minutes of audio usage for a full battery charge */
    private final double MAX_MINUTES = 720.0;

    /** base fee of 2 GB data plan */
    private final double base2GBplan = 50.0;

    /** adminstrative fee for the phone */
    private final double administrativeFee = 0.61;

    /**
     * Default Constructor
     */
    public MyPhone()
    {
        // initialise instance variables
        name = "Default";
        phoneNumber = "Default";
        numTexts = 0;
        dataUsage = 0.0;
        batteryLife = 0.0;

    }

    /**
     * Constructor with parameters for each instance variable
     * @param String - customer name
     * @param String - phone number
     */
    public MyPhone (String name, String num){
        this.name = name;
        setPhoneNumber(num);
    }

    /**
     * returns the number of texts
     * @return int number of texts
     */
    public int getNumTexts(){
        return numTexts;
    }

    /**
     * returns the battery life
     * @return double battery life
     */
    public double getBatteryLife() {
        return batteryLife;
    }    

    /**
     * returns amount of data consumed
     * @return double amount of data consumed
     */
    public double getDataUsage() {
        return dataUsage;
    }

    /**
     * changes the name to the string passed as parameter
     * @param String - customer name
     */
    public void setName (String n){
        name = n;
    }

    /**
     * sets the phone number to the value passed as parameter
     * @param String - phone number 
     */
    public void setPhoneNumber (String n){
        if (n.length() != 10)
            phoneNumber = "9999999999";
        else
            phoneNumber = n;
    }

    /**
     * phone number format
     * @return String phone number format
     */
    private String fmtPhoneNumber(){
        String num = "(" + phoneNumber.substring(0,3) + ")" 
            + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
        return num;
    }

    /**
     * updates the battery life by minutes charged
     * @param int - minutes need to be charged
     */
    public void chargeBattery (int mins){  
        if (mins > 0) {
            batteryLife += 1.0 / 120 * mins; 
            if (batteryLife > 1.0){
                batteryLife = 1.0;
            }
            DecimalFormat f1 = new DecimalFormat("#");
            JOptionPane.showMessageDialog(null, "Battery Life: " 
                + f1.format(batteryLife*100) + "%"); 
        }
    }

        /**
         * amount of data consumed and battery power after using data (calculated by mins)
         * @param int - minutes using data
         */
        public void streamAudio(int mins) {
        double minsLeft = 0.0;
        minsLeft = batteryLife * MAX_MINUTES;

        // prevent from having negative minutes
        if (mins > 0) {     
            if (mins > minsLeft){
                batteryLife = 0.0;
                dataUsage += minsLeft * DATA_PER_MIN;  
                JOptionPane.showMessageDialog(null, "Phone needs to be charged");
            }        
            else
            {
                batteryLife -= mins / MAX_MINUTES;
                dataUsage += mins * DATA_PER_MIN;  
            }           
        }
    }
 
    /**
     * increment the text counter
     * @param String - text
     */
    public void sendText (String text){
        numTexts += 1;
        JOptionPane.showMessageDialog(null, text);
    }

    /**
     * display the text
     */
    public void readTexts () {
        Random rand = new Random();
        int choice = rand.nextInt(5);

        // display a random message from 5 options
        switch (choice) {
            case 0:
            JOptionPane.showMessageDialog(null, "How is everything going?");
            break;

            case 1:
            JOptionPane.showMessageDialog(null, "Let's get some ice cream!");
            break;

            case 2:
            JOptionPane.showMessageDialog(null, "I'm so hungry!!!");
            break;

            case 3:
            JOptionPane.showMessageDialog(null, "I have no idea what is going on right now.");
            break;

            case 4:
            JOptionPane.showMessageDialog(null, "I need to find it.");
            break;

        }
    }

    /**
     * resets the data and number of texts for new month
     */
    private void startNewMonth(){ 
        numTexts = 0;
        dataUsage = 0;      
    }

    /**
     * calculates additional data fee
     * @return double - additional data fee
     */
    private double calcAddtionalDataFee() {
        // returns the additional data fee if applied
        if (dataUsage <= 2000)
            return 0.0;
        else 
            return Math.ceil((dataUsage - 2000) / 1000) * 15.0;
    }

    /**
     * calculates universal usage charge
     * @return double - universal usage charge
     */
    private double calcUsageCharge() {
        double calcUsageCharge = (base2GBplan + calcAddtionalDataFee()) * 0.03;
        return calcUsageCharge;
    }

    /**
     * calculates total charges
     * @return double - total charges
     */
    private double calcTotalFee() {        
        return base2GBplan + calcAddtionalDataFee() + calcUsageCharge() + administrativeFee;
    }

    /**
     * prints the monthly statement
     * \n - means new line
     * \t - means tab
     */
    public void printStatement () {
        NumberFormat f2 = NumberFormat.getCurrencyInstance();
        DecimalFormat f3 = new DecimalFormat("#.##");

        System.out.println("MyPhone Monthly Statement");
        System.out.println("");
        System.out.println("Customer:\t\t" + name + "\nNumber:\t\t\t" + fmtPhoneNumber() +    
            "\nTexts:\t\t\t" + numTexts + 
            "\nData usage:\t\t" + f3.format(dataUsage/1000) + " (GB)" +
            "\n2GB Plan:\t\t" + f2.format((base2GBplan)) + 
            "\nAdditional data fee:\t" + f2.format(calcAddtionalDataFee()) + 
            "\nUniversal Usage (3%):\t" + f2.format(calcUsageCharge()) + 
            "\nAdministrative Fee:\t" + f2.format(administrativeFee) +  
            "\nTotal Charges:\t\t" + f2.format(calcTotalFee()));
    }

}
 