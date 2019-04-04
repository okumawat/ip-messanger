package com.learn.ipm.main;

import java.util.Scanner;

import com.learn.ipm.server.Server;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ) throws InterruptedException
    {
       Server server = new Server(4321);
       Thread serverThread = new Thread(server);
       serverThread.start();
       Scanner sc = new Scanner(System.in);
       while(true) {
    	   String str = sc.nextLine();
    	   if(str!=null) {
    		   server.serverSays(str);
    	   }
    	   if(str.equals("stop")) {
    		   break;
    	   }
       }
       server.stopServer();
    }
}
