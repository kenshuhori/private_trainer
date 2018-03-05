package tokyo.kenshuhori_in.threads;

public class MultiRunnerTester {

	public void exec() {
		Thread t1 = new Thread(new InnerThread());
		t1.start();
		for (int i = 1; i < 4; i++) {
			if (i == 2) {
				try {
					t1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("スレッド1の" + i);
		}
	}

	class InnerThread implements Runnable {
		public void run() {
			for (int i = 1; i < 4; i++) {
				System.out.println("スレッド2の" + i);
			}
		}
	}

}
