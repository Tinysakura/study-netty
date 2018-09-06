package com.studynetty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable{
	String host;
	int port;
	Selector selector;
	SocketChannel socketChannel;
	
	volatile boolean stop = true;
	
	public TimeClientHandler(String host,int port){
		this.host = host;
		this.port = port;
		
		try {
			selector = selector.open();
			socketChannel = socketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(stop){
			try {
				selector.select(1000);
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				SelectionKey key = null;
				
				while(iterator.hasNext()){
					key = iterator.next();
					iterator.remove();
					
					try {
						handlerInput(key);
					} catch (IOException e) {
						e.printStackTrace();
						
						if(key != null){
							key.cancel();
							
							if(key.channel() != null){
								try {
									key.channel().close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(selector != null){
			try {
				selector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//����key
	private void handlerInput(SelectionKey key) throws IOException{
		if(key.isValid()){
			SocketChannel socketChannel = (SocketChannel)key.channel();
			if(key.isConnectable()){
				if(socketChannel.finishConnect()){//���������������˵�����
					socketChannel.register(selector, SelectionKey.OP_READ);
					doWrite(socketChannel);
				}else{
					//����ʧ�ܣ�ֱ���˳��߳�
					System.exit(1);
				}
			}
			
			if(key.isReadable()){//���ּ�����channel�ɶ�����channel�ж�ȡ����
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				
				int readBytes = socketChannel.read(byteBuffer);
				
				if(readBytes > 0){
					byteBuffer.flip();
					byte[] bytes = new byte[byteBuffer.remaining()];
					
					byteBuffer.get(bytes);
					String rec = new String(bytes,"UTF-8");
					System.out.println("rec2:"+rec);
					
					stop = false;
				}else if(readBytes < 0){
					selector.close();
					socketChannel.close();
				}else{
					//...
				}
			}
		}
	}

	public void doConnect() throws IOException{
		System.out.println("doConnect");
		if(socketChannel.connect(new InetSocketAddress(host,port))){//���ֱ�����ӳɹ�
			System.out.println("connect success");
			socketChannel.register(selector, SelectionKey.OP_READ);//ֱ�ӽ�channelע��Ϊ�ɶ���
			doWrite(socketChannel);//��������
		}else{
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	//����������Ϣ
	private void doWrite(SocketChannel socketChannel) throws IOException {
		String req = "QUERY_TIME";
		ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
		
		writeBuffer.put(req.getBytes());
		writeBuffer.flip();
		socketChannel.write(writeBuffer);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
