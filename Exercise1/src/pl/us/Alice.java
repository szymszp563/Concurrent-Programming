package pl.us;

public class Alice extends Thread{
	private Bob bob;

	public Alice(Bob bob) {
		super("Alice");
		this.bob = bob;
	}

	@Override
	public void run() {
		for(int i = 1; i<=10; i++) {
			System.out.println(String.format("[%s] Sending to Bob: %d", this.getName(), i));
			bob.setInput(i);
			bob.interrupt();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(String.format("[%s] Killing Bob!", this.getName()));
		bob.setInput(0);
		bob.interrupt();
	}
}
