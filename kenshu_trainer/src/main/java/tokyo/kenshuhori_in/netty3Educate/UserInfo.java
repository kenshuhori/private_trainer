package tokyo.kenshuhori_in.netty3Educate;

public class UserInfo {
	private long id;
    private String name;
    private int age;

    public UserInfo(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "id=" + this.id + ", name=" + this.name + ", age=" + this.age;
    }
}
