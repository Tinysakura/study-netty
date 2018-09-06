package com.studynetty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用线程池的Bio架构的服务端
 * @author Administrator
 *
 */
public class BioServer {
	
	public static void main(String[] args){
		System.out.println("server start");
		int port = 8888;
		int maxSize = 20;
		ServerSocket serverSocket = null;
		ExecutePool pool = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(port);
			pool = new ExecutePool(maxSize);
			
		    while(true){
				socket = serverSocket.accept();
				pool.execute(new TimeSeverHandler(socket));
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(serverSocket != null){
					serverSocket.close();
				}
				
				if(socket != null){
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(pool != null){
				pool.close();
			}
		}
	}
}
