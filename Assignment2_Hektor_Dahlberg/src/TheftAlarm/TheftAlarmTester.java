package TheftAlarm;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

public class TheftAlarmTester {

    @Test
    public void randomTester() throws Exception {
        System.out.println("-----Random-----");
        TheftAlarmModel alarm = new TheftAlarmModel();

        RandomTester tester= new RandomTester(alarm);
        System.out.println("Bulid graph");
        tester.buildGraph();
        tester.buildGraph().printGraphDot("randomModel.dot");
        printer(tester,2);
        printer(tester,16);
        printer(tester,32);
        printer(tester,64);
        task3_5(tester,64);


    }
    @Test
    public void greedyTester() throws Exception {
        System.out.println("-----Greedy-----");
        TheftAlarmModel alarm = new TheftAlarmModel();
        GreedyTester tester= new GreedyTester(alarm);
        System.out.println("Bulid graph");
        tester.buildGraph();
        tester.buildGraph().printGraphDot("greedyModel.dot");
        printer(tester,2);
        printer(tester,16);
        printer(tester,32);
        printer(tester,64);
        task3_5(tester,64);

    }
    @Test
    public void AllRoundTester() throws Exception {
        System.out.println("-----AllRound-----");
        TheftAlarmModel alarm = new TheftAlarmModel();
        AllRoundTester at= new AllRoundTester(alarm);
        at.setLoopTolerance(10);
        Tester tester=at;
        System.out.println("Bulid graph");
        tester.buildGraph();
        tester.buildGraph().printGraphDot("AllRoundModel.dot");
        printer(tester,2);
        printer(tester,16);
        printer(tester,32);
        printer(tester,64);
        task3_5(tester,64);


    }
    private void printer(Tester tester, int i){
        System.out.println(tester.buildGraph().getGraph());
        tester.addCoverageMetric(new ActionCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new TransitionCoverage());
        tester.addCoverageMetric(new TransitionPairCoverage());

        System.out.println("Generate " +i +" tests");
        tester.generate(i);
        System.out.println(" Coverage report "+i+":");
        tester.printCoverage();

    }
    private void task3_5(Tester tester, int i) {
        System.out.println(tester.buildGraph().getGraph());
        tester.addCoverageMetric(new ActionCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new TransitionCoverage());
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addListener(new VerboseListener());

        System.out.println("Generate task3_5 " +i +" tests");
        tester.generate(i);
        System.out.println(" Coverage report 3_5 "+i+":");
        tester.printCoverage();

    }
}
