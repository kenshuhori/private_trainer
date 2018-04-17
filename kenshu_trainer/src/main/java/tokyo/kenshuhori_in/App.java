package tokyo.kenshuhori_in;

public class App {

    public static void main( String[] args ) {
    	new App().execute();
    }

    private String target = "192.168.182.138";
    private String expDBUrl = "jdbc:oracle:thin:@192.168.1.1:1521:cp06";

    public App() {
    	super();
    }

    public void execute() {
    	System.out.println(getProductDB_URL());
    }

    protected String getProductDB_URL() {
        String host = expDBUrl.split(":")[3];
        if (host.toLowerCase().equals("@localhost")) {
            expDBUrl = expDBUrl.replace(host, "@" + getControllerIp());
        }
        return expDBUrl;
    }

    protected String getControllerIp() {
    	return target;
    }
}
