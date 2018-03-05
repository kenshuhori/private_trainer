package tokyo.kenshuhori_in.fileEducate;

import tokyo.kenshuhori_in.SubMainInterface;

public class FileEducateMain implements SubMainInterface {

	public static void main(String[] args) {
		new FileEducateMain().execute();
	}

	//forWindows
	String driveC = "C:\\";
	String driveX = "X:\\個人用";
	//forLinux
	String linuxRoot = "/";

	@Override
	public void execute() {
		new FileSpaceChecker().checkSpace(driveX);
	}
}
