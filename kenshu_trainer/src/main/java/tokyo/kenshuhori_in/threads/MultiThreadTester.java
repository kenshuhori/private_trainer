package tokyo.kenshuhori_in.threads;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadTester {

	private Integer total = 0;
	private List<Integer> hm = new ArrayList<Integer>();

	private class InnerThread extends Thread {

		private int curr;

		public InnerThread(int curr) {
			this.curr = curr;
		}

		@Override
		public void run() {

			try {
				double d = Math.random();
				long interval = Math.round(80.0 + d * 40.0);
				System.out.print("thread : " + curr + "; ");
				System.out.println("total before: " + total);
				Thread.sleep(interval);
				synchronized(hm) {
					int innerTotal = hm.get(0);
					for (int j = 0; j < 1000000; j++) {

					}
					innerTotal += curr;
					Thread.sleep(interval);
					hm.set(0, innerTotal);
					System.out.print("thread : " + curr + "; ");
					System.out.println("innerTotal: " + innerTotal);
					System.out.print("interval : " + interval + "; ");
					System.out.println("total after: " + hm.get(0));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void exec() {
		basicTest();
	}

	private void basicTest() {

		hm.add(total);
		for (int i = 0; i < 10; i++) {
			Thread t = new InnerThread(i);
			t.start();
		}
	}
}
