package pl.us;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bank {
	public static final int N = 10;
	private int[] balances = new int[N];
	private Lock[] locks = new Lock[N];

	public Bank() {
		for (int i = 0; i < locks.length; ++i) {
			locks[i] = new ReentrantLock();
		}
	}

	public void deposit(int accountId, int amount) {
		balances[accountId] += amount;
	}

	public int getBalance(int accountId) {
		return balances[accountId];
	}

	public boolean transfer(int fromAccount, int toAccount, int amount) {
		while (true) {
			if (locks[fromAccount].tryLock() && locks[toAccount].tryLock()) {
				try {
					if(balances[fromAccount] < amount) {
						return false;
					} else {
						balances[fromAccount] -= amount;
						balances[toAccount] += amount;
						System.out.println(String.format("Sending amount %d from Account %d(current balance %d) to Account %d(current balance %d)",
								amount, fromAccount, balances[fromAccount], toAccount, balances[toAccount]));
					}
				} finally {
					locks[fromAccount].unlock();
					locks[toAccount].unlock();
				}
				break;
			} else {
				locks[fromAccount].unlock();//sometimes we acquire first lock but second is locked - both have to be free
				Thread.yield();
			}
		}

		return true;
	}
}
