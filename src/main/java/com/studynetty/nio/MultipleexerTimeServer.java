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
 * 一个多路复用的时间服务类
 * @author Administrator
 *
 */
public class MultipleexerTimeServer implements Runnable{
	int port;
	Selector selector;
	ServerSocketChannel serverSocketChannel;
	
	//线程的终止标志
	volatile boolean stop = true;
	
	public MultipleexerTimeServer(int port){
		this.port = port;
		
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);//设置非阻塞
			serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);//设置监听端口并限制连接数量
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//SeverSocketChannel绑定selector，selector监听这条通道上的accept事件
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
				//获取当前被选择器选择的SelectionKey的集合
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
		
		//释放相关资源
		if(selector != null){
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//处理相关输入
	public void handlerInput(SelectionKey key)throws IOException{
		if(key.isValid()){//判断key的有效性
			if (key.isAcceptable()) {//处理新接入的请求消息
				ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
			    SocketChannel socketChannel = serverSocketChannel.accept();//接收对该通道的套接字连接
			    socketChannel.configureBlocking(false);
			    socketChannel.register(selector, SelectionKey.OP_READ);//将channel与selector绑定并注册监听事件为可读
			}
			if(key.isReadable()){
				SocketChannel socketChannel = (SocketChannel) key.channel();
				//使用ByteBuffer读取channel中的bytes
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				int readBytes = socketChannel.read(byteBuffer);
				
				if(readBytes > 0){//的确从channel中获取到了数据
					byteBuffer.flip();//读写状态的转换
					byte[] bytes = new byte[byteBuffer.remaining()];
					
					byteBuffer.get(bytes);//This method transfers bytes from this buffer into the given destination array.
				    String rec = new String(bytes,"UTF-8");
				    System.out.println("rec:"+rec);
				    
				    doWrite(rec,socketChannel);
				}else if(readBytes < 0){//说明对端链路已关闭
					System.out.println("<0");
					key.cancel();
					socketChannel.close();
				}else{
					//读到0字节，忽略即可
				}
			}
		}
	}

	//对读取到的数据做出响应
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
		writeBuffer.put(currentTime.getBytes());//将响应结果写入ByteBuffer
		writeBuffer.flip();
		socketChannel.write(writeBuffer);//向channel中写入响应信息，此时客户端的selector监听到这条channel可读于是读出响应结果完成一次请求交互的过程。
	
	    System.out.println("doWrite finish");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
