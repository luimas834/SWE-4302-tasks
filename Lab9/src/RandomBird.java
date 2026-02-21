public class RandomBird extends Bird implements Flyable {
    public RandomBird(String name) {
        super(name);
    }

    @Override
    public void fly() {
        System.out.println( "flying!");
    }
    
}
