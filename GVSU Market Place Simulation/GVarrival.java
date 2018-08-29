
/**
 * Generate a new Customer object and places it in line
 *
 * @author Trang Nguyen
 * @version November 2017
 */
public class GVarrival extends GVevent
{
    /** 
     * invoke GVevent class constructor to set the MarketPlace object and the event time
     * @param MarketPlace store
     * @param double time
     */
    public GVarrival (MarketPlace store, double time){
        super(store, time);
    }
    
    /**
     * place the customer in line
     */
    public void process () {
        store.customerGetsInLine();
    }
}
