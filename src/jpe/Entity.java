package jpe;

import java.util.HashMap;


public class Entity {

    public final String name;

    private double[] position;
    private double[] velocity;
    private double[] acceleration;
    private double mass;


    public Entity(String name, double mass) {
        this.name = name;

        this.position = new double[]{0, 0};
        this.velocity = new double[]{0, 0};
        this.acceleration = new double[]{0, 0};
        this.mass = mass;
    }

    public Entity(String name, double[] position, double[] velocity, double[] acceleration, double mass) {
        this.name = name;

        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
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
        HashMap<String, double[]> states = new HashMap<>();
        states.put("Position", this.position);
        states.put("Velocity", this.velocity);
        states.put("Acceleration", this.acceleration);
        return states;
    }
}
