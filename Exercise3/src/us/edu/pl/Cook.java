package us.edu.pl;

public class Cook extends Thread {
    Pot pot;
    public Cook(Pot pot) {
        this.pot = pot;
        setDaemon(true);
    }
    @Override
    public void run() {
        try {
            while(!isInterrupted()) {
                pot.fill();
            }
        } catch(InterruptedException e) {
            return;
        }
    }
}
