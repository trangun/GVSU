import java.util.*;
import java.io.*;

/**
 * Maintains an unlimited number of baby names
 * @author Trang Nguyen
 * @version October 2017
 */
public class BabyNamesDatabase {
    
    /** Array List in BabyName class */
    private ArrayList <BabyName> file;

    /**
     * Constructor for objects of class BabyNamesDatabase
     */
    public BabyNamesDatabase()
    {
        // initialise instance variables
        file = new ArrayList <BabyName> ();
    }

    /**
     * opens the provided filename and read all the data
     * @param String - filename
     */
    public void readBabyNameData(String filename) 
    {
        // Read the full set of data from a text file
        try{ 

            // open the text file and use a Scanner to read the text
            FileInputStream fileByteStream = new FileInputStream(filename);
            Scanner scnr = new Scanner(fileByteStream);
            scnr.useDelimiter("[,\r\n]+");

            // keep reading as long as there is more data
            while(scnr.hasNext()) {

                // FIX ME: read the name, gender, count and year
                String name = scnr.next();
                String gender = scnr.next();
                int count = scnr.nextInt();
                int year = scnr.nextInt();

                // FIX ME: assign true/false to boolean isFemale based on
                // the gender String
                boolean isFemale;

                if (gender.equals("F")) {
                    isFemale = true;
                } else {
                    isFemale = false;
                }

                // FIX ME: instantiate a new Baby Name and add to ArrayList
                file.add(new BabyName(name, isFemale, count, year)); 

            }
            fileByteStream.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: " + filename);
        }

    }
    
    /**
     * returns the number of items in the ArrayList
     * @return int number of items in the ArrayList
     */
    public int countAllNames () {
        return file.size();
    }

    /**
     * returns the total number of girls born since 1880
     * @return int total number of girls born since 1880
     */
    public int countAllGirls() {
        int countGirls = 0;
        
        for (BabyName bg : file) {
            if (bg.isFemale()){
                countGirls += bg.getCount();
            }
        }
        
        return countGirls;
    }

    /**
     * returns the total number of boys born since 1880
     * @return int total number of boys born since 1880
     */
    public int countAllBoys() {
        int countBoys = 0;
        
        for (BabyName bb : file) {
            if (!bb.isFemale()){
                countBoys += bb.getCount();
            }
        }
        
        return countBoys;
    }

    /**
     * returns the most popular girl name for a specific year
     * @return BabyName the most popular girl name for a specific year
     */
    public BabyName mostPopularGirl (int year) {
        BabyName nameGirl = new BabyName ("Trang", true, 0, 0);
        
        for (BabyName mpg : file) {
            if ((mpg.getYear() == year) && (mpg.getCount() > nameGirl.getCount()) && (mpg.isFemale())) {
                nameGirl = mpg;
            }
        }
         
        return nameGirl;
    }

     /**
     * returns the most popular boy name for a specific year
     * @return BabyName the most popular boy name for a specific year
     */
    public BabyName mostPopularBoy (int year) {
        BabyName nameBoy = new BabyName ("Trang", false, 0, 0);
        
        for (BabyName mpg : file) {
            if ((mpg.getYear() == year) && (mpg.getCount() > nameBoy.getCount()) && (!mpg.isFemale())) {
                nameBoy = mpg;
            }
        }
        
        return nameBoy;
    }

    /**
     * returns a new list of baby names that match the requested name that is sorted by number of babies
     * @param String - baby name
     * @return ArrayList <BabyName> a new list of baby names match the requested name
     */
    public ArrayList <BabyName> searchForName (String name) {
        ArrayList <BabyName> searchName = new ArrayList <BabyName> ();
        
        for (BabyName s : file) {
            if (name.equalsIgnoreCase(s.getName())){
                searchName.add(s);
            }
        }
        
        Collections.sort(searchName);
        return searchName;
    }

    /**
     * returns a new list of baby names that match the requested year that is sorted by number of babies
     * @param int - baby birth year
     * @return ArrayList <BabyName> list of baby names that match the requested year
     */
    public ArrayList <BabyName> searchForYear (int year) {
        ArrayList <BabyName> babyNamesByYear = new ArrayList <BabyName> ();
        
        for (BabyName s : file) {
            if (s.getYear() == year) {
                babyNamesByYear.add(s);
            }
        }
        
        Collections.sort(babyNamesByYear);
        return babyNamesByYear;
    }

    /**
     * returns a new list of the ten most popular baby names in a given year
     * @param int - baby birth year
     * @return ArrayList <BabyName> list of baby names in a given year
     */
    public ArrayList <BabyName> topTenNames (int year) {
        ArrayList <BabyName> tempList = searchForYear(year);   
        ArrayList <BabyName> tempTenList = new ArrayList <BabyName> ();
        
        for (int i = 0; i < 10; i++) {
            tempTenList.add(tempList.get(i));
        }
        
        return tempTenList;
    }    
}
