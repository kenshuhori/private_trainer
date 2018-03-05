package tokyo.kenshuhori_in.reflectionEducate;

public class ReflectionChild {

	private String name;

	public ReflectionChild() {
		super();
	}

	public void showMyName() {
		System.out.println("Hello, " + name);
	}

	public void setMyName(String name) {
		this.name = name;
	}
}
