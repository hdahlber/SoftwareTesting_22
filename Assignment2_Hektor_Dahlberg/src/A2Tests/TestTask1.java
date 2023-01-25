import org.junit.Before;
import org.junit.Test;
import premium.Premium;
import static org.junit.Assert.*;



public class TestTask1 {


    Premium premium;
    @Before
    public void setUp() {
        premium = new Premium();
    }



    @Test
    public void T1_P1() {
        assertEquals(-1,premium.liability(15, 'q', false));
        assertEquals(500,premium.liability(26, 'm', false));
        assertEquals(-1,premium.liability(96, 'm', false));

    }
    @Test
    public void T1_P2() {
        assertEquals(2000,premium.liability(24, 'm', false));
        assertEquals(300,premium.liability(24, 'f', true));

    }
    @Test
    public void T3_P3() {
        assertEquals(300,premium.liability(26, 'm', true));


    }
    @Test
    public void T4_P4() {
        assertEquals(200,premium.liability(47, 'm', true));
        assertEquals(300,premium.liability(44, 'm', true));
        assertEquals(300,premium.liability(66, 'm', true));

    }

}
