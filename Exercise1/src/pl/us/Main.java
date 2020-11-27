package pl.us;

public class Main {

    public static void main(String[] args) {
		Bob bob = new Bob();
		Alice alice = new Alice(bob);
		bob.start();
		alice.start();
    }
}
