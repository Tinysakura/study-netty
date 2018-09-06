package com.studynetty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * һ����·���õ�ʱ�������
 * @author Administrator
 *
 */
public class MultipleexerTimeServer implements Runnable{
	int port;
	Selector selector;
	ServerSocketChannel serverSocketChannel;
	
	//�̵߳���ֹ��־
	volatile boolean stop = true;
	
	public MultipleexerTimeServer(int port){
		this.port = port;
		
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);//���÷�����
			serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);//���ü����˿ڲ�������������
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//SeverSocketChannel��selector��selector��������ͨ���ϵ�accept�¼�
		    System.out.println("time server is start");
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		this.stop = false;
	}

	public void run() {	
		while(stop){
			try {
				selector.select(1000);
				//��ȡ��ǰ��ѡ����ѡ���SelectionKey�ļ���
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				SelectionKey selectionKey = null;
				
				while(iterator.hasNext()){
					selectionKey = iterator.next();
					iterator.remove();
					
					try {
						handlerInput(selectionKey);
					} catch (IOException e) {
						e.printStackTrace();
						
						if(selectionKey != null){
							selectionKey.cancel();
							
							if(selectionKey.channel() != null){
								try {
									selectionKey.channel().close();
								} catch (IOException e1) {
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
		
		//�ͷ������Դ
		if(selector != null){
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//�����������
	public void handlerInput(SelectionKey key)throws IOException{
		if(key.isValid()){//�ж�key����Ч��
			if (key.isAcceptable()) {//�����½����������Ϣ
				ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
			    SocketChannel socketChannel = serverSocketChannel.accept();//���նԸ�ͨ�����׽�������
			    socketChannel.configureBlocking(false);
			    socketChannel.register(selector, SelectionKey.OP_READ);//��channel��selector�󶨲�ע������¼�Ϊ�ɶ�
			}
			if(key.isReadable()){
				SocketChannel socketChannel = (SocketChannel) key.channel();
				//ʹ��ByteBuffer��ȡchannel�е�bytes
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				int readBytes = socketChannel.read(byteBuffer);
				
				if(readBytes > 0){//��ȷ��channel�л�ȡ��������
					byteBuffer.flip();//��д״̬��ת��
					byte[] bytes = new byte[byteBuffer.remaining()];
					
					byteBuffer.get(bytes);//This method transfers bytes from this buffer into the given destination array.
				    String rec = new String(bytes,"UTF-8");
				    System.out.println("rec:"+rec);
				    
				    doWrite(rec,socketChannel);
				}else if(readBytes < 0){//˵���Զ���·�ѹر�
					System.out.println("<0");
					key.cancel();
					socketChannel.close();
				}else{
					//����0�ֽڣ����Լ���
				}
			}
		}
	}

	//�Զ�ȡ��������������Ӧ
	private void doWrite(String rec, SocketChannel socketChannel) throws IOException {
		System.out.println("doWrite");
		String currentTime;
		if("QUERY_TIME".equalsIgnoreCase(rec)){
			currentTime = new Date(System.currentTimeMillis()).toString();
		}else{
			currentTime = "BAD_QUERY";
		}
		
		byte[] bytes = currentTime.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(currentTime.getBytes());//����Ӧ���д��ByteBuffer
		writeBuffer.flip();
		socketChannel.write(writeBuffer);//��channel��д����Ӧ��Ϣ����ʱ�ͻ��˵�selector����������channel�ɶ����Ƕ�����Ӧ������һ�����󽻻��Ĺ��̡�
	
	    System.out.println("doWrite finish");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
