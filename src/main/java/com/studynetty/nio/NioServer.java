package com.studynetty.nio;

/**
 * ʹ��nio�ķ����
 * @author Administrator
 *
 */
public class NioServer {
	public static void main(String[] args){
		int port = 8888;
		
		new Thread(new MultipleexerTimeServer(port)).start();
	}
}
