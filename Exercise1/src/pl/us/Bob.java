package pl.us;

public class Bob extends Thread {

	private int input = 1;

	public Bob() {
		super("Bob");
	}

	@Override
	public void run() {
		while(true) {
			if(interrupted()) {
				if(input != 0) {
					System.out.println(String.format("[%s] The result is: %d", this.getName(), 2 * this.input));
				} else {
					System.out.println(String.format("[%s] Finishing work.", this.getName()));
					break;
				}
			}
		}
	}

	public void setInput(int x) {
		this.input = x;
	}
}
