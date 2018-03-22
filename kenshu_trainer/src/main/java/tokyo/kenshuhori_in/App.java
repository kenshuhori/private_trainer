package tokyo.kenshuhori_in;

import tokyo.kenshuhori_in.jdbcEducate.TruncateEducatorForDBCopy;

public class App {

    public static void main( String[] args ) {
    	new TruncateEducatorForDBCopy(args).execute();
    }

    public App() {

    }

    public void execute() {

    }
}
