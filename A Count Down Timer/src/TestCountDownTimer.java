import org.junit.Test;

import static org.junit.Assert.*;
public class TestCountDownTimer {

    // Test Constructor without errors
    @Test
    public void testConstructorTUN() {
        CountDownTimer s = new CountDownTimer(10, 30);
        assertEquals(s.toString(), "0:10:30");

        s = new CountDownTimer ();
        assertEquals(s.toString(), "0:00:00");

        s = new CountDownTimer (9, 8);
        assertEquals(s.toString(), "0:09:08");

        s = new CountDownTimer (10, 8,9);
        assertEquals(s.toString(), "10:08:09");

        s = new CountDownTimer("20:9:7");
        assertEquals(s.toString(), "20:09:07");

        s = new CountDownTimer(4);
        assertEquals(s.toString(), "0:00:04");

        s = new CountDownTimer("4");
        assertEquals(s.toString(), "0:00:04");

        s = new CountDownTimer(10,2,3);
        CountDownTimer s2 = new CountDownTimer(s);
        assertEquals(s,s2);

        s = new CountDownTimer(2,3);
        s2 = new CountDownTimer(s);
        assertEquals(s,s2);

        s = new CountDownTimer(13);
        s2 = new CountDownTimer(s);
        assertEquals(s,s2);
    }

    // Test Constructor with errors
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN1() {
        new CountDownTimer("hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN2() {
        new CountDownTimer("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN3() {
        new CountDownTimer(-2, -4,-6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN4() {
        new CountDownTimer(-4,-6,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN5() {
        new CountDownTimer(2, -4,-6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN6() {
        new CountDownTimer(2, -4,6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN7() {
        new CountDownTimer(-2, 4, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN8() {
        new CountDownTimer(2, 4, -6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN9() {
        new CountDownTimer(-2, -4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN10() {
        new CountDownTimer(2, -4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN11() {
        new CountDownTimer(-2, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN12() {
        new CountDownTimer(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN13() {
        new CountDownTimer(10, 100, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN14() {
        new CountDownTimer(10, 20, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN15() {
        new CountDownTimer(100, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN16() {
        new CountDownTimer(10, 200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN17() {
        new CountDownTimer(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN18() {
        new CountDownTimer("10:200:300");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN19() {
        new CountDownTimer("10:200:30");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN20() {
        new CountDownTimer("10:20:300");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN21() {
        new CountDownTimer("200:300");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN22() {
        new CountDownTimer("200:30");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN23() {
        new CountDownTimer("20:300");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN24() {
        new CountDownTimer("200");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN25() {
        new CountDownTimer("0:00:hi");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN26() {
        new CountDownTimer("0:hi:00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN27() {
        new CountDownTimer("hi:04:53");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN28() {
        new CountDownTimer("hi:53");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN29() {
        new CountDownTimer("53:hi");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithErrorsTUN30() {
        CountDownTimer nullTimer = null;
        new CountDownTimer(nullTimer);
    }

    // Test Sub method without errors
    @Test
    public void testSubMethodTUN() {
        CountDownTimer s1 = new CountDownTimer(5, 01, 30);
        s1.sub(2000);
        assertEquals(s1.toString(), "4:28:10");

        s1.sub(-2000);
        assertEquals(s1.toString(), "5:01:30");

        s1 = new CountDownTimer(5, 59, 30);
        CountDownTimer s2 = new CountDownTimer(1, 1, 35);
        s1.sub(s2);
        assertEquals(s1.toString(), "4:57:55");

        for (int i = 0; i < 14000; i++) {
            s1.dec();
        }
        assertEquals(s1.toString(), "1:04:35");
    }

    // Test Sub Method with errors
    @Test (expected = IllegalArgumentException.class)
    public void testSubErrorsTUN1() {
        CountDownTimer s1 = new CountDownTimer(1000);
        s1.suspend(false);
        s1.sub(4000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSubErrorsTUN2() {
        CountDownTimer s1 = new CountDownTimer(1,10);
        s1.suspend(false);
        s1.sub(4300);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSubErrorsTUN3() {
        CountDownTimer s1 = new CountDownTimer(1,1,1);
        s1.sub(5000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSubErrorsTUN4() {
        CountDownTimer s1 = new CountDownTimer(5, 1, 30);
        CountDownTimer s2 = new CountDownTimer(7,1,30);
        s1.suspend(false);
        s1.sub(s2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSubErrorsTUN5() {
        CountDownTimer s1 = new CountDownTimer( 1, 30);
        CountDownTimer s2 = new CountDownTimer(2,30);
        s1.suspend(false);
        s1.sub(s2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSubErrorsTUN6() {
        CountDownTimer s1 = new CountDownTimer(30);
        CountDownTimer s2 = new CountDownTimer(35);
        s1.suspend(false);
        s1.sub(s2);
    }

    // Test add method without errors
    @Test
    public void testAddMethodTUN() {
        CountDownTimer s1 = new CountDownTimer(5, 1, 30);
        s1.suspend(false);
        s1.add(2000);
        assertEquals(s1.toString(), "5:34:50");

        s1 = new CountDownTimer(5, 59, 30);
        CountDownTimer s2 = new CountDownTimer(1, 2, 35);
        s1.add(s2);
        assertEquals(s1.toString(), "7:02:05");

        for (int i = 0; i < 15000; i++) {
            s1.inc();
        }
        assertEquals(s1.toString(), "11:12:05");
    }

    // Test add method with errors
    @Test (expected = IllegalArgumentException.class)
    public void testAddErrorsTUN1() {
        CountDownTimer s1 = new CountDownTimer(0, 5, 10);
        s1.suspend(false);
        s1.add(-2000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddErrorsTUN2() {
        CountDownTimer s1 = new CountDownTimer(1,10);
        s1.suspend(false);
        s1.add(-2000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddErrorsTUN3() {
        CountDownTimer s1 = new CountDownTimer(10);
        s1.suspend(false);
        s1.add(-2000);
    }

    // Test equals method and hash code method
    @Test
    public void testEqualsAndHashCodeTUN() {
        CountDownTimer s1 = new CountDownTimer(5, 59, 00);
        CountDownTimer s2 = new CountDownTimer(5, 59, 23);
        CountDownTimer s3 = new CountDownTimer("5:59:00");
        assertFalse(s1.equals(s2));
        assertTrue(s1.equals(s3));
        assertTrue(s1.hashCode() == s3.hashCode());

        s1 = new CountDownTimer(59,2);
        s2 = new CountDownTimer(2,2);
        s3 = new CountDownTimer("02:02");
        assertFalse(CountDownTimer.equals(s1,s2));
        assertTrue(CountDownTimer.equals(s2,s3));
        assertTrue(s2.hashCode() == s3.hashCode());

        s1 = new CountDownTimer();
        assertEquals(false, s1.equals(null));

    }

    // test compareTo method
    @Test
    public void testCompareToTUN() {
        CountDownTimer s1 = new CountDownTimer(5, 59, 00);
        CountDownTimer s2 = new CountDownTimer(6, 01, 00);
        CountDownTimer s3 = new CountDownTimer(5, 50, 20);
        CountDownTimer s4 = new CountDownTimer("5:59:00");

        assertTrue(s2.compareTo(s1) > 0);
        assertTrue(s3.compareTo(s1) < 0);
        assertTrue(s1.compareTo(s4) == 0);
        assertFalse(s2.compareTo(s1) == 0);

        assertEquals(0, CountDownTimer.compareTo(s1,s4));
        assertEquals(1, CountDownTimer.compareTo(s2,s1));
        assertEquals(-1, CountDownTimer.compareTo(s3,s1));
    }

    // Test Load Save Method without errors
    @Test
    public void testLoadSaveTUN() {
        CountDownTimer s1 = new CountDownTimer (5,59,30);
        CountDownTimer s2 = new CountDownTimer ("5:59:30");

        s1.save("saveIt");
        s1 = new CountDownTimer ();  // resets to zero

        s1.load("saveIt");
        assertTrue (s1.equals(s2));

        s2.save("saveIt");
        s2 = new CountDownTimer();

        s2.load("saveIt");
        assertTrue(s2.equals(s1));
    }


    // Test suspend method
    @Test
    public void testSuspendTUN() {
        CountDownTimer s1 = new CountDownTimer(1,2,3);
        s1.suspend(true);
        s1.setHours(10);
        s1.setMinutes(40);
        s1.setSeconds(30);
        assertEquals(s1.toString(), "1:02:03");

    }

    // Test setters method
    @Test
    public void testSettersTUN() {
        CountDownTimer s = new CountDownTimer(10,2,3);
        s.setHours(11);
        s.setMinutes(12);
        s.setSeconds(48);
        assertEquals(s.toString(),"11:12:48");
    }

    // Test save method with errors
    @Test (expected = IllegalArgumentException.class)
    public void testSaveWithErrorTUN(){
        CountDownTimer s = new CountDownTimer();
        s.save(null);
    }

    // Test load method with errors
    @Test (expected = IllegalArgumentException.class)
    public void testLoadWithErrorTUN(){
        CountDownTimer l = new CountDownTimer();
        l.load(null);
    }
}
