import jpe.*;


public class Test {

    public static Environment env = new Environment(10 , 1);
    public static Entity e1 = new Entity("Entity1", 1);
    public static Entity e2 = new Entity("Entity2", 1);

    public static Actions actions = new Actions(Execution.Parallel) {
        
        @Override
        public void formulate() {
            Physics.accelerate(e1, new double[]{2, 1});
            Physics.accelerate(e2, new double[]{1, 0});
        }
    };

    public static void main(String[] args) {
        env.addEntity(e1);
        env.addEntity(e2);
        env.run(actions);
    }
}