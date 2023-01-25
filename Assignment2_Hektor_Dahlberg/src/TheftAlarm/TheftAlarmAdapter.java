package TheftAlarm;
import theft_alarm_v6.Alarm;
import static org.junit.Assert.*;
public class TheftAlarmAdapter {
    Alarm sut= new Alarm();

    public void resetSUT(){
        sut.reset();
    }

    public void openDoorsSUT(int expected) {
        assertEquals(expected, sut.openDoors());
    }
    public void closeDoorsSUT(int expected) {
        assertEquals(expected, sut.closeDoors());
    }
    public void armSUT(int expected) {
        assertEquals(expected, sut.arm());
    }
    public void disarmSUT(int expected) {
        assertEquals(expected, sut.disarm());
    }
}
