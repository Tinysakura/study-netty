package com.studynetty.nio;

public class NioClient {
	public static void main(String[] args){
		String host = "127.0.0.1";
		int port = 8888;
		
		new Thread(new TimeClientHandler(host, port)).start();
	}
}
