package A1Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vending_machine.VendingMachine;
import static org.junit.Assert.*;


public class TestsTask3 {
    VendingMachine vend;
    @Before
    public void setUp() {

        vend= new VendingMachine(3);


    }


    @Test
    public void t1() {
        assertEquals("",vend.displayReturningCoins(0.0).trim());
    }
    @Test
    public void t2() {
        assertEquals("1 x 2 Euro",vend.displayReturningCoins(2.0).trim());
    }
    @Test
    public void t3() {
        assertEquals("1 x 1 Euro",vend.displayReturningCoins(1.0).trim());
    }
    @Test
    public void t4() {
        assertEquals("1 x 50 Cent",vend.displayReturningCoins(0.5).trim());
    }
    @Test
    public void t5() {
        assertEquals("1 x 20 Cent",vend.displayReturningCoins(0.2).trim());
    }


    @Test
    public void calculateReturningCoins() {
        int[] list = vend.calculateReturningCoins(3.70);
    }
}