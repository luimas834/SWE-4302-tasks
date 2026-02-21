public class Main {
    public static void main(String[] args) throws Exception {
        Penguin penguin = new Penguin("peng");
        RandomBird randomBird = new RandomBird("Random");

        System.out.println(penguin.getName());
        System.out.println(randomBird.getName());
        randomBird.fly();
    }
}
