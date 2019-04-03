package com.learn.ipm.main;

import com.learn.ipm.server.Server;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ) throws InterruptedException
    {
       Server.bindClient();
       Thread.sleep(60000);
       Server.stopServer();
    }
}
