package us.edu.pl;

import java.util.concurrent.Semaphore;

public class Pot {
    static final int M = 5;  // Pot capacity
    private Semaphore emptyPot = new Semaphore(1);
    private Semaphore available = new Semaphore(0);
    private int servingsAvailable = 0;
    private int totalServedCount = 0;

    private synchronized void insertServings(int value) {
        servingsAvailable = value;
    }

    private synchronized int removeServing() {
        --servingsAvailable;
        ++totalServedCount;
        return servingsAvailable;
    }

    public int getTotalServedCount() {
        return totalServedCount;
    }

    public void getServing() throws InterruptedException {
        // Your solution here
        if(servingsAvailable == 0 && !available.tryAcquire()) {
            emptyPot.release();
            available.acquire();
        }
        removeServing();
    }

    public void fill() throws InterruptedException {
        if(emptyPot.tryAcquire()){
            insertServings(M);
            available.release(5);
        }
    }
}
