package jpe;

import java.util.ArrayList;


public class Environment {

    public static final double ACC_GRAV = 9.8;
    private static double timeInstant;
    protected ArrayList<Entity> entities;
    protected static double timeStep;
    protected static double incline;

    public Environment(double timeStep) {
        incline = 0;
        timeInstant = 0;
        entities = new ArrayList<>();
        Environment.timeStep = timeStep;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void setIncline(double incline) {
        Environment.incline = incline;
    }

    public static double getTimeInstant() {
        return timeInstant;
    }

    public static void incrementTimeInstant() {
        timeInstant += 1;
    }

    public void runSequential(Execution execution) {
        execution.define();
    }

    public void runParallel(Execution execution, double repeat) {
        for (double time = Environment.getTimeInstant(); time < (repeat + Environment.getTimeInstant()); time += Environment.timeStep) {
            System.out.println("[TIME " + time + "]");
            execution.define();
        }
        Environment.incrementTimeInstant();
    }
}