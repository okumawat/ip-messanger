package com.learn.ipm.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.learn.ipm.server.InputStreamThread;
import com.learn.ipm.server.OutputStreamThread;

public class ClientThread {
	
	private static final String host = "localhost";
	
	public static void main(String[] args) {
		Socket client = null;
		String port = "4321";
		try {
			client = new Socket(host,Integer.parseInt(port));
			Thread input = new Thread(new InputStreamThread(client.getInputStream()));
			Thread output = new Thread(new OutputStreamThread(client.getOutputStream()));
			input.start();
			output.start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
