package tokyo.kenshuhori_in.pomVer;

import java.io.File;

public class PomVerChangeMain {

	private static String cu_home = "C:\\pleiadesCU\\workspace\\updater_1";

	public static void main(String[] args) {
		new PomVerChangeMain().execute();
	}

	public void execute() {
		File file = new File(cu_home);
		if (file.exists()) {
			for (File one : file.listFiles()) {
				if (one.getName().lastIndexOf("pom.xml") >= 0) {
					System.out.println(one.getName());
				}
			}
		}
	}
}
