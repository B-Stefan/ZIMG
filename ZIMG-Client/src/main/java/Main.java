import container.JettyEmbeddedRunner;
import container.TomcatEmbeddedRunner;
import org.apache.catalina.LifecycleException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(final String[] args) throws IOException,LifecycleException {
		System.out
				.println("### STARTING EMBEDDED WEB CONTAINER\nPlease select your container...\n1) Jetty\n2) Tomcat\n");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(
				System.in));
		String input = bufferRead.readLine();
		if ("1".equals(input)) {
			System.out.println("Starting Jetty ..");
			new JettyEmbeddedRunner().startServer();
		} else if ("2".equals(input)) {
			System.out.println("Starting Tomcat ..");
			new TomcatEmbeddedRunner().startServer();
		} else {
			System.out.println("Nothing selected .. quitting application...");
		}

	}
}
