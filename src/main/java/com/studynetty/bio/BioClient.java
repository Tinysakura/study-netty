package com.studynetty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Bio通信的服务端
 * @author Administrator
 *
 */
public class BioClient {
	public static void main(String[] args){
		String ip = "127.0.0.1";
		int port = 8888;
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			socket = new Socket(ip,port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			
			System.out.println("query send");
			out.println("QUERY_TIME");
			
			String rec = in.readLine();
			System.out.println("rec:"+rec);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//关闭各种流
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
