package com.learn.ipm.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server implements Runnable {

	private final String hostname = "localhost";
	private int port;
	private ServerSocket serverSocket;
	private boolean listening =true;
	private List<Socket> clients;
	private int totalClients=0;
	
	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<>();
	}
	private ServerSocket getServerConnection(){
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(hostname, port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverSocket;

	}
	
	public synchronized void stopServer() {
		this.listening=false;
		try {
			this.serverSocket.close();
			System.out.println("Server socket connection closed.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void serverSays(String message) {
		for(Socket client:clients) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(client.getOutputStream());
				writer.write("Server:"+message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}finally {
				if(writer!=null) {
					writer.close();
				}
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		getServerConnection();
		System.out.println("Server listening..");
		while(listening) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				totalClients++;
				clients.add(clientSocket);
				Thread clientHandler = new Thread(new ClientHandler(clientSocket));
				clientHandler.setName("Client "+totalClients);
				
				clientHandler.start();
				System.out.println("Client connected:"+clientHandler.getName());
			} catch (Exception e) {
				if(!listening) {
					break;
				}	
				System.out.println("Server disconnected:"+e.getMessage());
			}
		}

		System.out.println("Server disconnected");
	}
	
}
