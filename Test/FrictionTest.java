import jpe.*;

public class FrictionTest {

    public static Environment env = new Environment(1);
    public static Entity e1 = new Entity("Entity1", 1, true);
    public static Parameter p1 = new Parameter(Action.FORCE);

    public static void define() throws IncorrectParameterError {
        env.addEntity(e1);
        p1.defineForce(new double[]{2,1});
        p1.defineFriction(new double[] {0.1, 0});
    }

    public static Execution exec1 = new Execution() {
        @Override
        public void define() {
            e1.subjectTo(p1, 3, true);
        }
    };

    public static void main(String[] args) throws IncorrectParameterError {
        define();
        env.runSequential(exec1);
    }
}
