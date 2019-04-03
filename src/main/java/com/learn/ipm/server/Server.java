package com.learn.ipm.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static final String hostname = "localhost";
	private static final int port = 4321;
	private static ServerSocket serverSocket;
	private static boolean listening =true;
	
	private static ServerSocket getServerConnection(){
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(hostname, port));
			System.out.println("Server socket connection created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverSocket;

	}
	
	public static void stopServer() {
		try {
			serverSocket.close();
			System.out.println("Server socket connection closed.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void bindClient() {
		ServerSocket server = getServerConnection();
		listening = true;
		System.out.println("Server listening..");
		while(listening) {
			try {
				Socket client = server.accept();
				Thread input = new Thread(new InputStreamThread(client.getInputStream()));
				Thread output = new Thread(new OutputStreamThread(client.getOutputStream()));
				input.start();
				output.start();
			} catch (Exception e) {
				listening=false;	
				System.out.println("Server disconnected:"+e.getMessage());
			}
		}
		
	}
	
}
