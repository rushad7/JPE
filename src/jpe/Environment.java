package jpe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;


public class Environment {

    protected ArrayList<Entity> entities;

    protected static double timeStep;
    protected final double[] time;


    public Environment(int simTimeStop, double timeStep) {
        entities = new ArrayList<>();

        Environment.timeStep = timeStep;
        this.time = IntStream.range(0, simTimeStop).mapToDouble(i -> (double) i).toArray();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    private void runParallel(Actions actions) {

        for (double timeInstant : time) {

            actions.formulate();

            System.out.println("--------------------------");
            System.out.println("[ITERATION " + (int) timeInstant + "]");
            System.out.println("--------------------------");

            for (Entity entity : entities) {
                System.out.println("Entity: " + entity.name);
                HashMap<String, double[]> states = entity.inspectStates();
                states.forEach((String state, double[] vector) -> System.out.println(state + " " + Arrays.toString(vector)));
                System.out.println("--------------------------");
            }

            System.out.println("\n##########################\n");
        }
    }

    private void runSequential() {

    }

    public void run(Actions actions) {

        if (actions.execution == Execution.Parallel) {
            runParallel(actions);
        } else if (actions.execution == Execution.Sequential) {
            runSequential();
        }

    }
}