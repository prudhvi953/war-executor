package com.imaginea.embeddedserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbeddedServer {
	public static void main(String[] args) throws Exception {
		if (args.length > 1) {
			String warPath = args[0];
			Server server = new Server(Integer.parseInt(args[1]));

			WebAppContext handler = new WebAppContext();
			handler.setContextPath("/");
			handler.setWar(warPath);

			server.setHandler(handler);
			server.start();
			server.join();
		} else {
			throw new IllegalArgumentException(
					"At least two arguments war_file_name and port");
		}
	}
}