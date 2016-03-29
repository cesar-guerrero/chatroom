package com.shuffle.app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import freemarker.template.Configuration;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

public class Server {

	public Server() {
		
	}
	
	public void Start() {
		System.out.println("[Server] : server set up");
		Spark.port(5555);
		Spark.externalStaticFileLocation("src/main/resources/static");
	    Spark.exception(Exception.class, new ExceptionPrinter());
	    FreeMarkerEngine freeMarker = createEngine();
	    
	    // Setup Spark Routes
	    Spark.get("/home", new FrontHandler(), freeMarker);
	}

	private static FreeMarkerEngine createEngine() {

		Configuration config = new Configuration();
		File templates = new File(
				"src/main/resources/spark/template/freemarker");
		try {
			config.setDirectoryForTemplateLoading(templates);
		} catch (IOException ioe) {
			System.out.printf("ERROR: Unable use %s for template loading.\n",
					templates);
			System.exit(1);
		}
		return new FreeMarkerEngine(config);
	}

	private class FrontHandler implements TemplateViewRoute {
		@Override
		public ModelAndView handle(Request req, Response res) {
			Map<String, String> variables = ImmutableMap.of("title", "Bacon");
			return new ModelAndView(variables, "home.ftl");
		}
	}
	
	private static class ExceptionPrinter implements ExceptionHandler {
		@Override
		public void handle(Exception e, Request req, Response res) {
			res.status(500);
			StringWriter stacktrace = new StringWriter();
			try (PrintWriter pw = new PrintWriter(stacktrace)) {
				pw.println("<pre>");
				e.printStackTrace(pw);
				pw.println("</pre>");
			}
			res.body(stacktrace.toString());
		}
	}	
}
