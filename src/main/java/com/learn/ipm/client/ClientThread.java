package com.learn.ipm.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.learn.ipm.server.InputStreamThread;
import com.learn.ipm.server.OutputStreamThread;

public class ClientThread {
	
	private static final String host = "localhost";
	private static final int port = 4321;
	
	public static void main(String[] args) {
		Socket client = null;
		try {
			client = new Socket(host, port);
			Thread input = new Thread(new InputStreamThread(client.getInputStream()));
			Thread output = new Thread(new OutputStreamThread(client.getOutputStream()));
			input.start();
			output.start();
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
