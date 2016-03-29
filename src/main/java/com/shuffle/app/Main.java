package com.shuffle.app;

/**
 * Hello world!
 *
 */
public class Main {
	
	private String[] args;
	private Server server;
	
    public static void main( String[] args ) {
    	new Main(args).run();
    }
    
    private Main(String[] args) {
    	this.args = args;
    	this.server = new Server();
    }
    
    private void run() {
    	System.out.println(args);
    	server.Start();
    }
}
