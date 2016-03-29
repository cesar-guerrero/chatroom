package com.shuffle.app;

/**
 * Hello world!
 *
 */
public class App {
	
	private String[] args;
	private Server server;
	
    public static void main( String[] args ) {
    	new App(args).run();
    }
    
    private App(String[] args) {
    	this.args = args;
    	this.server = new Server();
    }
    
    private void run() {
    	System.out.println("[App] : chatroom program started");
    	server.Start();
    }
}
