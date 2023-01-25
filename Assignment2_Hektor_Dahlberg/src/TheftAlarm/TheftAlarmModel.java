package TheftAlarm;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
public class TheftAlarmModel implements FsmModel {

    private enum State {ARMED_OPEN,ARMED_CLOSED,DISARMED_OPEN,DISARMED_CLOSED};
    private State state;
    private int blinks= 0;
    private boolean ready= false;

    TheftAlarmAdapter adapter = new TheftAlarmAdapter();

    @Override
    public Object getState() {
        return state;
    }

    @Override
    public void reset(boolean b) {
        state = State.DISARMED_OPEN;
        blinks = 0;
        ready=false;
        adapter.resetSUT();
    }

    @Action
    public void arm() {
        ready=true;
        if (state==State.DISARMED_CLOSED){
            blinks=1;
            state=State.ARMED_CLOSED;
        }

        else{
            blinks=0;
        }
        adapter.armSUT(blinks);
    }
    @Action
    public void disarm() {
        ready= false;
        if(state==State.ARMED_OPEN){
            blinks=2;
            state=State.DISARMED_OPEN;
        }
        else if(state==State.ARMED_CLOSED){
            blinks=2;
            state=State.DISARMED_CLOSED;
        }
        else{
            blinks=0;
        }
        adapter.disarmSUT(blinks);
    }
    @Action
    public void closeDoors() {
        if(state==State.ARMED_OPEN){
            blinks=1;
            state=State.ARMED_CLOSED;
        }
        else if(state==State.DISARMED_OPEN && !ready){
            blinks=0;
            state=State.DISARMED_CLOSED;
        }
        else if(state==State.DISARMED_OPEN && ready){
            blinks=1;
            state=State.ARMED_CLOSED;
        }

        else{
            blinks=-1;
        }
        adapter.closeDoorsSUT(blinks);
    }
    @Action
    public void openDoors() {
        if(state==State.ARMED_CLOSED){
            blinks=27;
            state=State.ARMED_OPEN;
        }
        else if(state==State.DISARMED_CLOSED){
            blinks=0;
            state=State.DISARMED_OPEN;
        }
        else{
            blinks=-1;
        }
        adapter.openDoorsSUT(blinks);
    }
    public boolean openDoorsGuard() {
        return state != State.ARMED_OPEN && state != State.DISARMED_OPEN;



    }
    public boolean closeDoorsGuard() {
        return state != State.ARMED_CLOSED && state != State.DISARMED_CLOSED;
    }

}
