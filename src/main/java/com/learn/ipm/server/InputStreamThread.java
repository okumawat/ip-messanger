package com.learn.ipm.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamThread implements Runnable {

	private InputStream inputStream;
	
	public InputStreamThread(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}

	@Override
	public void run() {
		boolean read=true;
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
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
				// TODO Auto-generated catch block
				read=false;
				e.printStackTrace();
			}
		}	
		try {
			reader.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
