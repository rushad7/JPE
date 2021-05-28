package jpe;


public class Parameter {

    protected final Action action;
    protected double[] acceleration;
    protected double[] force;

    public Parameter(Action action) {
        this.action = action;
    }

    public void defineAcceleration(double[] acceleration) throws IncorrectParameterError {
        if (this.action == Action.ACCELERATION) {
            this.acceleration = acceleration;
        } else {
            throw new IncorrectParameterError("Did not expect action 'ACCELERATE' here.");
        }
    }

    public void defineForce(double[] force) throws IncorrectParameterError {
        if (this.action == Action.FORCE) {
            this.force = force;
        } else {
            throw new IncorrectParameterError("Did not expect action 'FORCE' here.");
        }
    }

}

