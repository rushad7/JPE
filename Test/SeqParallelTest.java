import jpe.*;

public class SeqParallelTest {

    public static Environment env = new Environment(1);

    public static Entity e1 = new Entity("Entity1", 1);
    public static Entity e2 = new Entity("Entity2", new double[]{1, 1},
            new double[]{0, 0}, new double[]{0, 0}, 1);

    public static Parameter p1 = new Parameter(Action.ACCELERATION);
    public static Parameter p2 = new Parameter(Action.ACCELERATION);

    public static void define() throws IncorrectParameterError {
        env.addEntity(e1);
        env.addEntity(e2);

        p1.defineAcceleration(new double[]{2, 1});
        p2.defineAcceleration(new double[]{2, 1});
    }

    public static Execution exec1 = new Execution() {
        @Override
        public void define() {
            e1.subjectTo(p1, 3, true);
            e2.subjectTo(p2, 2, true);
        }
    };

    public static Execution exec2 = new Execution() {
        @Override
        public void define() {
            e1.subjectTo(p1);
            e2.subjectTo(p2);
        }
    };

    public static void main(String[] args) throws IncorrectParameterError {
        define();
        env.runSequential(exec1);
        env.runParallel(exec2, 2);
        System.out.println(Environment.getTimeInstant());
    }
}
