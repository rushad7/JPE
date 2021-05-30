package jpe;


public class Parameter {

    protected final Action action;
    protected double[] acceleration;
    protected double[] force;
    protected double[] coefficientOfFriction;

    public Parameter(Action action) {
        this.action = action;
        coefficientOfFriction = new double[]{0, 0};
    }

    public void defineAcceleration(double[] acceleration) throws IncorrectParameterError {
        if (this.action == Action.ACCELERATION) {
            this.acceleration = acceleration;
        } else {
            throw new IncorrectParameterError("Expected action 'ACCELERATE' here.");
        }
    }

    public void defineForce(double[] force) throws IncorrectParameterError {
        if (this.action == Action.FORCE) {
            this.force = force;
        } else {
            throw new IncorrectParameterError("Expected action 'FORCE' here.");
        }
    }

    public void defineFriction(double[] coefficientOfFriction) {
        this.coefficientOfFriction = coefficientOfFriction;
    }

}

