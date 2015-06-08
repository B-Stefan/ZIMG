package container;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;
import servlet.DatePrintServlet;


import java.io.File;

public class TomcatEmbeddedRunner {
	public void startServer() throws LifecycleException {

        // setup server
        final Tomcat tomcat = new Tomcat();

        tomcat.setBaseDir("."); // location where temp dir is created
        tomcat.setPort(8080);

        // configure context
        final File applicationPath = new File("ZIMG-Client/src/webapp/");
        Context rootContext = tomcat.addContext("/", applicationPath.getAbsolutePath());
        rootContext.addLifecycleListener(new ContextConfig());
        // JSP and Default Servlet setup, mime type mapping and welcome files
        Tomcat.initWebappDefaults(rootContext);

        // start server
        tomcat.start();
        tomcat.getServer().await();

	}
}
