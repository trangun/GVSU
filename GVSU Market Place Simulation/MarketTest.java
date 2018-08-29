
/**
 * Write a description of class MarketTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MarketTest
{
    /**
     * automatically test various methods
     */
    public static void main(String args[]){
        System.out.println("Start testing...");

        // does store start with 3 cashiers?
        MarketPlace myStore = new MarketPlace();
        assert(myStore.getNumCashiers() == 3) : "Start with 3 cashiers";
        
        // // how many customers served with default arrival time
        myStore.startSimulation();
        int before = myStore.getNumCustomersServed();

        // are parameters updated correctly?
        myStore.setParameters(4, 5.2, 1.5, true);
        assert(myStore.getNumCashiers() == 4) : "Change to 4 cashiers";
        myStore.startSimulation();
        
        // test results
        System.out.println(myStore.getReport());
        
        // how many customers served with quicker arrival times?
        int after = myStore.getNumCustomersServed();
        assert(before < after) : "Should be more customers";

        System.out.println("Testing complete.");
    }
}
