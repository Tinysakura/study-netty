package com.studynetty.nio;

/**
 * 使用nio的服务端
 * @author Administrator
 *
 */
public class NioServer {
	public static void main(String[] args){
		int port = 8888;
		
		new Thread(new MultipleexerTimeServer(port)).start();
	}
}
