import jpe.CollisionResolver;
import jpe.Entity;
import jpe.Environment;

import java.util.Arrays;

public class CollisionTest {

    public static Environment env = new Environment(1);
    public static Entity e1 = new Entity("Entity1", 1, true);
    public static Entity e2 = new Entity("Entity2", 1, true);

    public static void main(String[] args) {
        e1.setVelocity(new double[]{1, 0});
        e2.setPosition(new double[] {2, 0});
        e2.setVelocity(new double[]{-1, 0});

        CollisionResolver cr = new CollisionResolver(e1, e2, 0.1);
        cr.updateStates();

        var statesE1 = e1.inspectStates();
        statesE1.forEach((String state, double[] vector) -> System.out.println(state + " " + Arrays.toString(vector)));
        System.out.println("\n");
        var statesE2 = e2.inspectStates();
        statesE2.forEach((String state, double[] vector) -> System.out.println(state + " " + Arrays.toString(vector)));
    }
}
