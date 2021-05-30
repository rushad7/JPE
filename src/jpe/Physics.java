package jpe;


public class Physics {

    public Physics() {
    }

    public static void accelerate(Entity entity, Parameter parameter) {
        double vx = entity.getVelocity()[0] +
                (parameter.acceleration[0] - parameter.coefficientOfFriction[0] * Environment.ACC_GRAV * Math.cos(Environment.incline)) * Environment.timeStep;
        double vy = entity.getVelocity()[1] +
                (parameter.acceleration[1] - parameter.coefficientOfFriction[1] * Environment.ACC_GRAV * Math.cos(Environment.incline)) * Environment.timeStep;
        entity.setVelocity(new double[]{vx, vy});

        double px = entity.getPosition()[0] + vx * Environment.timeStep;
        double py = entity.getPosition()[1] + vy * Environment.timeStep;
        entity.setPosition(new double[]{px, py});
    }

    public static void applyForce(Entity entity, Parameter parameter) {
        double vx = entity.getVelocity()[0] +
                (((parameter.force[0] / entity.getMass())
                        - parameter.coefficientOfFriction[0] * Environment.ACC_GRAV * Math.cos(Environment.incline)) * Environment.timeStep);
        double vy = entity.getVelocity()[1] +
                (((parameter.force[1] / entity.getMass())
                        - parameter.coefficientOfFriction[1] * Environment.ACC_GRAV * Math.cos(Environment.incline)) * Environment.timeStep);
        entity.setVelocity(new double[]{vx, vy});

        double px = entity.getPosition()[0] + vx * Environment.timeStep;
        double py = entity.getPosition()[1] + vy * Environment.timeStep;
        entity.setPosition(new double[]{px, py});
    }
}

