import org.junit.Before;
import org.junit.Test;
import premium.Premium;
import static org.junit.Assert.*;



public class TestTask2 {


    Premium premium;

    @Before
    public void setUp() {
        premium = new Premium();
    }


    @Test
    public void T2_undifined() {
        assertEquals(-1, premium.liability(17, 'q',true));
        assertEquals(-1, premium.liability(17,'m',true));
        assertEquals(-1, premium.liability(17,'f',true));
        assertEquals(-1, premium.liability(999,'q',false));
        assertEquals(-1, premium.liability(999,'m',false));
        assertEquals(-1, premium.liability(999,'f',false));
        assertEquals(-1, premium.liability(21,'q',true));
        assertEquals(-1, premium.liability(35,'q',false));
        assertEquals(-1, premium.liability(60,'q',true));
        assertEquals(-1, premium.liability(70,'q',false));
        assertEquals(-1, premium.liability(35,'q',true));
        assertEquals(-1, premium.liability(999,'m',true));

    }
    @Test
    public void T2_2000() {
        assertEquals(2000, premium.liability(21,'m',false));
    }
    @Test
    public void T2_200() {
        assertEquals(200, premium.liability(60, 'f', true));
        assertEquals(200, premium.liability(60, 'm', true));
    }

    @Test
    public void T2_300() {
        assertEquals(300, premium.liability(21, 'f', true));
        assertEquals(300, premium.liability(35, 'm', true));
        assertEquals(300, premium.liability(35, 'f', false));
        assertEquals(300, premium.liability(70, 'm', true));
        assertEquals(300, premium.liability(70, 'f', false));
        assertEquals(300, premium.liability(35, 'f', true));
        assertEquals(300, premium.liability(21, 'm', true));
        assertEquals(300, premium.liability(21, 'f', false));
    }
    @Test
    public void T2_400() {
        assertEquals(400, premium.liability(60, 'm', false));
    }
    @Test
    public void T2_500() {
        assertEquals(500, premium.liability(35, 'm', false));
    }
}