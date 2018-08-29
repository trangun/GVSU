import java.text.DecimalFormat;
/**
 * Stores birth information about a single name for specific year
 *
 * @author Trang Nguyen
 * @version October 2017
 */
public class BabyName implements Comparable
{
    /** name of the babies */
    private String name;
    
    /** gender of the babies */
    private boolean gender;
    
    /** number of newborn babies in given name*/
    private int numsBaby;
    
    /** birth year of the babies */
    private int year;
    
    /** Default Constructor */
    public BabyName() {
        name = "Default";
        gender = true;
        numsBaby = 0;
        year = 0;
    }

    /**
     * Constructor with parameters for each instance variable
     * @param String - baby name
     * @param boolean - baby gender
     * @param int - number of babies
     * @param int - birth year of babies
     */
    public BabyName(String n, boolean g, int count, int yr)
    {
        name = n;
        gender = g;
        numsBaby = count;
        year = yr;
    }

    /**
     * Compare two BabyName objects with respect to the number of births
     * @param Object - BabyName objects
     * @return int number of babies
     */

    public int compareTo (Object other) {
        BabyName b = (BabyName) other;
        return (b.numsBaby - numsBaby);
    }
    
    /**
     * returns the gender of the babies
     * @return boolean the gender of the babies
     */
    public boolean isFemale() {
        return (gender);
    }

    /**
     * returns name of the babies
     * @return String name of the babies
     */
    public String getName () {
        return name;
    }

    /**
     * returns number of the babies
     * @return int number of the babies
     */
    public int getCount () {
        return numsBaby;
    }

    /**
     * returns birth year of the babies
     * @return int birth year of the babies
     */
    public int getYear () {
        return year;
    }

    /**
     * change the gender to the boolean passed as parameter
     * @param boolean - gender
     */
    public void setGender (boolean g) {
        gender = g;
    }

    /**
     * change the name to the string passed as parameter
     * @param String - babies name
     */
    public void setName (String n) {
        name = n;
    }

    /**
     * change the number of babies to the int passed as parameter
     * @param int - number of babies
     */
    public void setNumsBaby (int count) {
        numsBaby = count;
    }

    /**
     * change the birth year to the int passed as parameter
     * @param int - babies' birth year
     */
    public void setYear (int yr) {
        year = yr;
    }

    /**
     * converts the object of the class BabyName to a String
     * @return String 
     */
    public String toString() {
        DecimalFormat f1 = new DecimalFormat("#,###");
        if (gender) {
            return "" + f1.format(numsBaby) + " girls named " + name + " in " + year;
        } else {
            return "" + f1.format(numsBaby) + " boys named " + name + " in " + year;

        }
    }

    /** 
     * test each of the method in BabyName class
     */
    public static void main (String [] args) {
        BabyName b1 = new BabyName ("Krystal", true, 12321452, 1995);
        System.out.println(b1);
        BabyName b2 = new BabyName ("Max", false, 12425234, 1989);
        System.out.println(b2);
    }
}
