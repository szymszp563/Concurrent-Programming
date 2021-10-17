package us.edu.pl;

public class Person extends Thread {
    Pot pot;
    int servingsConsumed = 0;
    public Person(String name, Pot pot) {
        super(name);
        this.pot = pot;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; ++i) {
                pot.getServing();
                ++servingsConsumed;
                Thread.yield();
            }
        } catch(InterruptedException e) {
            return ;
        }
    }
}
