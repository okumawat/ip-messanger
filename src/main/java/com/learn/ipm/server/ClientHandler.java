package com.learn.ipm.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{

	private Socket socket;
	
	public ClientHandler(Socket clientSocket) {
		this.socket = clientSocket;
	}
	
	@Override
	public void run() {
		boolean read=true;
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(read) {
			try {
				String data =reader.readLine();
				if(data!=null) {
					System.out.println(data);
				}
				if(data.equals("stop")) {
					read=false;
				}
			} catch (IOException e) {
				read=false;
				e.printStackTrace();
			}
		}	
	
	}

}
