import java.util.ArrayList;
/**
 * To test method in BabyNamesDatabase method
 *
 * @author Trang Nguyen
 * @version October 2017
 */
public class BabyTest
{
    public static void main(String args[]){
        BabyNamesDatabase db = new BabyNamesDatabase();

        // read small data file created just for testing
        db.readBabyNameData("Test.txt");

        // check number of records
        if (db.countAllNames() != 15) {
            System.out.println("Error: Number of names should be 15");
        }

        // check number of baby girls
        if (db.countAllGirls() < 1000) {
            System.out.println("Error: Number of baby girls should be greater than 1000");
        }

        // check number of baby boys
        if (db.countAllBoys() < 1000) {
            System.out.println("Error: Number of baby boys should be greater than 1000");
        }

        // check most popular boy
        BabyName popular1 = db.mostPopularBoy(1995);
        String name1 = popular1.getName();
        if (name1.equals("Tim") == false){
            System.out.println("Error: Popular boy in 1995 should be Tim");
        }

        // check most popular girl
        BabyName popular2 = db.mostPopularGirl(1995);
        String name2 = popular2.getName();
        if(name2.equals("Annie") == true){
            System.out.println("Error: Popular girl in 1995 should be Annie");
        }

        // check name
        ArrayList <BabyName> tempList3 = db.searchForName("Annie");

        if (tempList3.size() == 0){
            System.out.println("Error: Should be at least one baby named Annie");
        }

        // check top ten names
        try {
            ArrayList <BabyName> tempList0 = db.topTenNames(1995);

            if (tempList0.size() != 10){
                System.out.println("Error: Should be 10 records in 1995");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Should be 10 records in 1995");
        }

        // check number of records for one year
        ArrayList <BabyName> tempList2 = db.searchForYear(1995);

        if (tempList2.size() != 5){
            System.out.println("Error: Should be 5 records in 1995");
        }

        System.out.println("Scanning complete.");
    }
}
