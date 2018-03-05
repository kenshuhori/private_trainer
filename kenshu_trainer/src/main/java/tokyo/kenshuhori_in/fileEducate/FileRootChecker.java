package tokyo.kenshuhori_in.fileEducate;

import java.io.File;

public class FileRootChecker {

	public void checker(File file) {
		File root = file.getAbsoluteFile().toPath().getRoot().toFile();
		String strRoot = file.getAbsoluteFile().toPath().getRoot().toString();
		System.out.println("root : " + strRoot);
	}
}
