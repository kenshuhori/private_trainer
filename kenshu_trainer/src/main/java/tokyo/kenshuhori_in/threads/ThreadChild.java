package tokyo.kenshuhori_in.threads;

public class ThreadChild extends Thread {

	private int threadId;
	private String threadName;

	public ThreadChild(int threadId, String threadName) {
		this.threadId = threadId;
		this.threadName = threadName;
	}

	@Override
	public void run() {
		System.out.println("threadId : " + threadId);
		System.out.println(" //// threadName : " + threadName);
	}
}
