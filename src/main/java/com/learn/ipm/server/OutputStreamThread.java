package com.learn.ipm.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class OutputStreamThread implements Runnable {

	private OutputStream outputStream;
	
	
	public OutputStreamThread(OutputStream outputStream) {
		super();
		this.outputStream = outputStream;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
		boolean write=true;
		Scanner sc = new Scanner(System.in);
		while(write) {
			String str = sc.nextLine();
			try {
				writer.write(str);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				write=false;
			}
			if(str.equals("stop")) {
				write=false;
			}
		}
		
		try {
			writer.close();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
