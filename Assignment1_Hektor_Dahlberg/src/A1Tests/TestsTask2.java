package A1Tests;

import org.junit.Before;
import org.junit.Test;
import vending_machine.VendingMachine;




import static org.junit.Assert.*;

public class TestsTask2 {
    VendingMachine vend;
    @Before
    public void setUp() {
        vend= new VendingMachine(3);
    }

    @Test
    public void t1() {
        int[] list = vend.calculateReturningCoins(3.70);
        assertEquals(1,list[0]);
        assertEquals(1,list[1]);
        assertEquals(1,list[2]);
        assertEquals(1,list[3]);


    }
    @Test
    public void t2() {
        int[] list = vend.calculateReturningCoins(1.0);
        assertEquals(1,list[1]);

    }
    @Test
    public void t3() {
        int[] list = vend.calculateReturningCoins(0.20);
        assertEquals(1,list[3]);
    }
    @Test
    public void t4() {
        int[] list = vend.calculateReturningCoins(2.00);
        assertEquals(1,list[0]);

    }
    @Test
    public void t5() {
        int[] list = vend.calculateReturningCoins(2.50);
        assertEquals(1,list[0]);
        assertEquals(1,list[2]);

    }
}