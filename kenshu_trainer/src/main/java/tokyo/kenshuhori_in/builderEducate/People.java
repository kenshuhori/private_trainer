package tokyo.kenshuhori_in.builderEducate;

public class People {
	private String name;
	private int age;
	private String hobby;
	
	public People() {
		super();
	}
	public People(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.hobby = builder.hobby;
	}

	public void execute() {
		String introduction = new Builder("hori", 25).hobby("cycling").build().hello();
		System.out.println(introduction);
	}
	
	public String hello() {
		return "Hello, I'm " + name + " and " + age + "years old. hobby is " + hobby;
	}
	
	static class Builder {
		private String name;
		private int age;
		private String hobby;
		
		public Builder(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		Builder hobby(String hobby) {
			this.hobby = hobby;
			return this;
		}
		
		People build() {
			return new People(this);
		}
	}
}