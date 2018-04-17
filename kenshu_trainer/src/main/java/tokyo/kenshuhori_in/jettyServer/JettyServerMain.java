package tokyo.kenshuhori_in.jettyServer;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServerMain {

	private static Server server;
    private static String base = "./WebContent";
    private static int httpsPort = 4524;
    private static int ftpPort = 10000;

	public static void main(String[] args) throws Exception {
		start(args);
	}

	public static void start(String[] args) throws Exception {
		server = new Server();

		//setup the constraint that causes all http requests to return a !403 error
        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        Constraint constraint = new Constraint();
        constraint.setDataConstraint(Constraint.DC_CONFIDENTIAL);

        //makes the constraint apply to all uri paths
        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec("/*");
        mapping.setConstraint(constraint);
        security.addConstraintMapping(mapping);

		ServerConnector httpsConnector = getHttpsConnector(httpsPort);
		server.setConnectors(new Connector[]{httpsConnector});

		// Web app handlers
        WebAppContext app = new WebAppContext(server, base, "/");
        app.setHandler(security);

     // Start app
        server.start();
        server.join();
	}

	private static ServerConnector getHttpsConnector(int port) {
        HttpConfiguration https = new HttpConfiguration();
        https.setSecurePort(port);
        https.setSecureScheme("https");
        https.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(JettyServerMain.class.getResource("/keystore.jks").toExternalForm());
        sslContextFactory.setKeyStorePassword("123456");
        sslContextFactory.setKeyManagerPassword("123456");

        ServerConnector sslConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(port);

        return sslConnector;
    }
}
