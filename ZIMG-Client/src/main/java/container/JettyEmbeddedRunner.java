package container;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.DatePrintServlet;

public class JettyEmbeddedRunner {
	public void startServer() {
		try {
			Server server = new Server();
			ServerConnector c = new ServerConnector(server);
			c.setIdleTimeout(1000);
			c.setAcceptQueueSize(10);
			c.setPort(8080);
			c.setHost("localhost");
			ServletContextHandler handler = new ServletContextHandler(server,
					"/app", true, false);
			ServletHolder servletHolder = new ServletHolder(
					DatePrintServlet.class);
			handler.addServlet(servletHolder, "/date");
			server.addConnector(c);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
