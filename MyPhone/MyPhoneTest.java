
/**
 * MyPhoneTest - used to test the MyPhone class.
 *
 * @author Trang Nguye
 * @version October 2017
 */
public class MyPhoneTest
{
    public static void main(String [] args) {
    // instantiating objects of the MyPhone class
    // default constructor
    MyPhone test = new MyPhone();
    
    // sencond constructor
    MyPhone mine = new MyPhone("Amanda Jaffe", "6163041847");
    MyPhone yours = new MyPhone("Mackinac Hall", "6666666666"); 
    
    mine.chargeBattery(120);
    mine.streamAudio(720);
    mine.chargeBattery(120);
    mine.streamAudio(360);
    mine.streamAudio(200);
    mine.sendText("Hello!");
    mine.printStatement();
    
    // yours.chargeBattery(120);
    // yours.streamAudio(700);
    // yours.chargeBattery(120);
    // yours.streamAudio(700);
    // yours.chargeBattery(120);
    // yours.streamAudio(700);
    // yours.printStatement();
    
    // for (int i = 1; i <= 6; i++) {
        // yours.chargeBattery(120);
        // yours.streamAudio(700);
    // }
    
    // for (int i = 1; i <= 3; i++) {
        // yours.sendText("Message");
    // }
    
    // yours.printStatement();
    
    // System.out.println("");
    // System.out.println("Testing user errors");
    // test.chargeBattery(-69);
    // test.chargeBattery(2000);
    // test.streamAudio(-10000);
    // test.printStatement();
    }
}
