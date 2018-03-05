package tokyo.kenshuhori_in.builderEducate;

import tokyo.kenshuhori_in.SubMainInterface;

public class BuilderEducateMain implements SubMainInterface {
	public static void main(String[] args) {
		new BuilderEducateMain().execute();
	}

	public BuilderEducateMain() {
		super();
	}

	@Override
	public void execute() {
		new People().execute();
	}

}
