package jpe;


public class Physics {

    public Physics() {
    }

    public static void accelerate(Entity entity, double[] acceleration) {
        double vx = entity.getVelocity()[0] + acceleration[0] * Environment.timeStep;
        double vy = entity.getVelocity()[1] + acceleration[1] * Environment.timeStep;
        entity.setVelocity(new double[]{vx, vy});

        double px = entity.getPosition()[0] + vx * Environment.timeStep;
        double py = entity.getPosition()[1] + vy * Environment.timeStep;
        entity.setPosition(new double[]{px, py});
    }

    public static void applyForce(Entity entity, double[] force) {
        double vx = entity.getVelocity()[0] + ((force[0] / entity.getMass()) * Environment.timeStep);
        double vy = entity.getVelocity()[1] + ((force[1] / entity.getMass()) * Environment.timeStep);
        entity.setVelocity(new double[]{vx, vy});

        double px = entity.getPosition()[0] + vx * Environment.timeStep;
        double py = entity.getPosition()[1] + vy * Environment.timeStep;
        entity.setPosition(new double[]{px, py});
    }
}

