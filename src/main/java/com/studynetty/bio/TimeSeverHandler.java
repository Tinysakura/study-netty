package com.studynetty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeSeverHandler implements Runnable{
	private Socket socket;
	
	public TimeSeverHandler(Socket socket){
		System.out.println("handler create");
		this.socket = socket;
	}

	public void run() {
		System.out.println("run");
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			String rec = null;
			
			while(true){
				rec = in.readLine();
				if(rec == null){
					break;
				}
				
				System.out.println("rec:"+rec);
				
				String currentTime;
				
				currentTime = "QUERY_TIME".equalsIgnoreCase(rec)?new Date(System.currentTimeMillis()).toString():"BAD_QUERY";
			    out.println(currentTime);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(out != null){
				out.close();
			}
			
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
