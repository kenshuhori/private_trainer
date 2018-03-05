package tokyo.kenshuhori_in.threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadMain {
	List<Thread> threadList = new ArrayList<Thread>();

	public void execute() {
		for(int i = 0; i < 5; i++) {
			threadList.add(new ThreadChild(i, "hori"));
		}
		threadList.forEach(Thread::start);
	}
}
