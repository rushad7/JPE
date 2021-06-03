package jpe;

import java.util.Arrays;
import java.util.HashMap;


public class Entity {

    public final String name;

    private double[] position;
    private double[] velocity;
    private double[] acceleration;
    private double[] angularAcceleration;
    private double mass;

    protected HashMap<String, double[]> states;

    public Entity(String name, double mass, boolean gravity) {
        this.name = name;
        this.position = new double[]{0, 0};
        this.velocity = new double[]{0, 0};
        if (gravity) {
            this.acceleration = new double[]{0, -9.8};
        } else {
            this.acceleration = new double[]{0, 0};
        }
        this.angularAcceleration = new double[]{0, 0};
        this.mass = mass;

        this.states = new HashMap<>();
    }

    public Entity(String name, double[] position, double[] velocity, double[] acceleration, double mass) {
        this.name = name;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new double[]{acceleration[0], (acceleration[1] - 9.8)};
        this.angularAcceleration = new double[]{0, 0};
        this.mass = mass;

        this.states = new HashMap<>();
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double[] getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double[] acceleration) {
        this.acceleration = acceleration;
    }

    public void setAngularAcceleration(double[] angularAcceleration) {
        this.angularAcceleration = angularAcceleration;
    }

    public double getMass() {
        return this.mass;
    }

    public void increaseMass(double mass) {
        if (mass >= 0) {
            this.mass += mass;
        }
    }

    public void decreaseMass(double mass) {
        if ((mass >= 0) && (this.mass > mass)) {
            this.mass -= mass;
        }
    }

    public HashMap<String, double[]> inspectStates() {
        states.put("Position", this.position);
        states.put("Velocity", this.velocity);
        states.put("Acceleration", this.acceleration);
        states.put("Angular Acceleration", this.angularAcceleration);
        return states;
    }

    private void printStates(double time) {
        System.out.println("--------------------------");
        System.out.println("[TIME " + time + "]");
        System.out.println("--------------------------");
        System.out.println("Entity: " + this.name);
        HashMap<String, double[]> states = this.inspectStates();
        states.forEach((String state, double[] vector) -> System.out.println(state + " " + Arrays.toString(vector)));
        System.out.println("--------------------------");
        System.out.println("\n##########################\n");
    }

    private void printStates() {
        System.out.println("--------------------------");
        System.out.println("Entity: " + this.name);
        HashMap<String, double[]> states = this.inspectStates();
        states.forEach((String state, double[] vector) -> System.out.println(state + " " + Arrays.toString(vector)));
        System.out.println("--------------------------");
        System.out.println("\n##########################\n");
    }


    public void subjectTo(Parameter parameter, double timePeriod, boolean inspectAllStates) {
        double timeInstant = Environment.getTimeInstant();
        for (double t = timeInstant; t < (timePeriod + timeInstant); t += Environment.timeStep) {
            if (parameter.action == Action.ACCELERATION) {
                this.acceleration = parameter.acceleration;
                Physics.accelerate(this, parameter);
            } else if (parameter.action == Action.FORCE) {
                this.acceleration = new double[]{parameter.force[0] / this.mass, parameter.force[1] / this.mass};
                Physics.applyForce(this, parameter);
            }
            if (inspectAllStates) {
                printStates(Environment.getTimeInstant());
            }
            Environment.incrementTimeInstant();
        }
        if (!inspectAllStates) {
            printStates(timePeriod);
        }
    }

    public void subjectTo(Parameter parameter) {
        if (parameter.action == Action.ACCELERATION) {
            this.acceleration = parameter.acceleration;
            Physics.accelerate(this, parameter);
        } else if (parameter.action == Action.FORCE) {
            Physics.applyForce(this, parameter);
        }
        printStates();
    }

    public double kineticEnergy() {
        double velocityMagnitude = Math.sqrt(Math.pow(velocity[0], 2) + Math.pow(velocity[1], 2));
        return 0.5 * mass * Math.pow(velocityMagnitude, 2);
    }
}
