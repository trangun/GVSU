
/**
 * Relates to a particular cashier. Customers pay the cashier and leave the store
 * 
 * @author Trang Nguyen
 * @version November 2017
 */
public class GVdeparture extends GVevent
{
    /**
     * invoke the GVevent class constructor to set the MarketPlace object, the event time, and cashier ID
     * @param MarketPlace store
     * @param double event time
     * @param int Cashier ID
     */
    public GVdeparture (MarketPlace store, double time, int id) {
        super(store, time, id);
    }
    
    /** 
     * Customers pay the cashier
     */
    public void process () {
        
        store.customerPays(getCashier());
    }
}
