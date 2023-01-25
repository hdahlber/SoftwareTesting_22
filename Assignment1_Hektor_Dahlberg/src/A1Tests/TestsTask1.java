package A1Tests;

import org.junit.Before;
import org.junit.Test;
import vending_machine.VendingMachine;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;






public class TestsTask1 {

    VendingMachine vend;
    @Before
    public void setUp() {
        vend= new VendingMachine(3);
    }



    @Test
    public void calculateChange() {
        assertEquals(fixDecimal(0.00),vend.calculateChange(4.7,"WW OE WW TC FC OE TE WW"));
        assertEquals(fixDecimal(0.00),vend.calculateChange(4.7,"QW"));
        assertEquals(fixDecimal(0.20),vend.calculateChange(4.7,"TC"));
        assertEquals(fixDecimal(0.70),vend.calculateChange(4.7,"FC"));
        assertEquals(fixDecimal(1.70),vend.calculateChange(4.7,"OE"));
        assertEquals(fixDecimal(3.70),vend.calculateChange(4.7,"TE"));
    }
    private BigDecimal fixDecimal(double number) {

        return BigDecimal.valueOf(number).setScale(2, RoundingMode.FLOOR);
    }
}