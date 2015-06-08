package container;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import servlet.DatePrintServlet;

import java.io.File;

public class TomcatEmbeddedRunner {
	public void startServer() throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		File base = new File(System.getProperty("java.io.tmpdir"));
		Context rootCtx = tomcat.addContext("/app", base.getAbsolutePath());
		Tomcat.addServlet(rootCtx, "dateServlet", new DatePrintServlet());
		rootCtx.addServletMapping("/date", "dateServlet");
		tomcat.start();
		tomcat.getServer().await();
	}
}
