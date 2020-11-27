package pl.us;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Accountant extends Thread {
	Bank bank;

	public Accountant(Bank bank) {
		this.bank = bank;
	}

	@Override
	public void run() {
		Random rng = ThreadLocalRandom.current();
		for (int i = 0; i < 1000; ++i) {
			// Try to transfer a random amount between a pair of accounts
			// The accounts numbers (ids) are also selected randomly
			int fromAccount = rng.nextInt(Bank.N - 1);
			int toAccount = rng.nextInt(Bank.N - 1);
			while (toAccount == fromAccount) { // Source should differ from
				// the target
				toAccount = rng.nextInt(Bank.N - 1); // Try again
			}
			bank.transfer(fromAccount, toAccount, rng.nextInt(100));
		}
	}
}
