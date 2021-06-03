package jpe;

import java.util.Arrays;

public class CollisionResolver {

    private final Entity entity1;
    private final Entity entity2;
    private final double coefficientOfRestitution;

    public CollisionResolver(Entity entity1, Entity entity2, double coefficientOfRestitution) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.coefficientOfRestitution = coefficientOfRestitution;
    }

    private double[][] computeFinalVelocity() {
        double A = ((entity1.getMass() - (this.coefficientOfRestitution * entity2.getMass())) / (entity1.getMass() + entity2.getMass()));
        double B = (((1 + this.coefficientOfRestitution) * entity2.getMass()) / (entity1.getMass()) + entity2.getMass());
        double C = (((1 + this.coefficientOfRestitution) * entity1.getMass()) / (entity1.getMass()) + entity2.getMass());
        double D = ((entity2.getMass() - (this.coefficientOfRestitution * entity1.getMass())) / (entity1.getMass() + entity2.getMass()));

        double vx1 = (A * entity1.getVelocity()[0]) + (B * entity2.getVelocity()[0]);
        double vy1 = (A * entity1.getVelocity()[1]) + (B * entity2.getVelocity()[1]);
        double vx2 = (C * entity1.getVelocity()[0]) + (C * entity2.getVelocity()[0]);
        double vy2 = (C * entity1.getVelocity()[1]) + (D * entity2.getVelocity()[1]);

        double[] v1 = new double[]{vx1, vy1};
        double[] v2 = new double[]{vx2, vy2};

        return new double[][]{v1, v2};
    }

    private double[][] computeAcceleration() {
        double[][] velocity = this.computeFinalVelocity();
        double[] v1 = velocity[0];
        double[] v2 = velocity[1];

        double ax1 = (v1[0] - entity1.getVelocity()[0]) * Environment.timeStep;
        double ay1 = (v1[1] - entity1.getVelocity()[1]) * Environment.timeStep;
        double ax2 = (v2[0] - entity2.getVelocity()[0]) * Environment.timeStep;
        double ay2 = (v2[1] - entity2.getVelocity()[1]) * Environment.timeStep;

        double[] a1 = new double[]{ax1, ay1};
        double[] a2 = new double[]{ax2, ay2};

        return new double[][]{a1, a2};
    }

    private double[] computeAngle() {
        double[][] v = this.computeFinalVelocity();
        double[] v1 = v[0];
        double[] v2 = v[1];

        double v1Length = Math.sqrt(Math.pow(v1[0], 2) + Math.pow(v1[1], 2));
        //double v2Length = Math.sqrt(Math.pow(v2[0], 2) + Math.pow(v2[1], 2));

        double v1Theta = Math.acos(v1[0] / v1Length);
        double v2Theta = Math.asin(((entity1.getMass() * v1[1]) / (entity2.getMass() * v2[1])) * Math.sin(v1Theta));

        return new double[]{v1Theta, v2Theta};
    }

    public void updateStates() {

        double[][] velocity = this.computeFinalVelocity();
        double[] v1 = velocity[0];
        double[] v2 = velocity[1];

        entity1.setVelocity(v1);
        entity2.setVelocity(v2);

        double[][] acceleration = this.computeAcceleration();
        double[] a1 = acceleration[0];
        double[] a2 = acceleration[1];

        entity1.setAcceleration(a1);
        entity2.setAcceleration(a2);

        double[] theta = computeAngle();
        double v1Theta = theta[0];
        double v2Theta = theta[1];

        System.out.println(Arrays.toString(theta));

        double positionX1Trans = ((entity1.getPosition()[0] * Math.cos(v1Theta)) - (entity1.getPosition()[1] * Math.sin(v1Theta)));
        double positionY1Trans = ((entity1.getPosition()[0] * Math.sin(v1Theta)) - (entity1.getPosition()[1] * Math.cos(v1Theta)));
        double positionX2Trans = ((entity2.getPosition()[0] * Math.cos(v2Theta)) - (entity2.getPosition()[1] * Math.sin(v2Theta)));
        double positionY2Trans = ((entity2.getPosition()[0] * Math.sin(v2Theta)) - (entity2.getPosition()[1] * Math.cos(v2Theta)));

        entity1.setPosition(new double[]{positionX1Trans, positionY1Trans});
        entity2.setPosition(new double[]{positionX2Trans, positionY2Trans});
    }
}
