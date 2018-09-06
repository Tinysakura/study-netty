package com.studynetty.FixedLengthFrameDecoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * ����netty��ʱ��ͻ��˿���
 * @author Administrator
 *
 */
public class NettyClient {
	
	public void connect(String host,int port){
		EventLoopGroup group = new NioEventLoopGroup();//�����ͻ��˵��߳���
		
		try {		
			Bootstrap bootstrap = new Bootstrap();
			
			bootstrap.group(group).channel(NioSocketChannel.class)
			    .option(ChannelOption.TCP_NODELAY, true)
			    .handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new FixedLengthFrameDecoder(10));//���tcp��ճ���������
						ch.pipeline().addLast(new StringDecoder());//���tcp��ճ���������
						ch.pipeline().addLast(new TimeClientHandler());
					}
				});
			
			//�����첽���Ӳ���
			ChannelFuture f = bootstrap.connect(host, port);
			System.out.println("client connected");
			
			//�ȴ��ͻ�����·�ر�
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}
	
	private class TimeClientHandler extends ChannelHandlerAdapter{
		byte[] req;
		int counter;
		
		public TimeClientHandler(){
			req = ("helloNetty").getBytes();
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			ctx.close();
			cause.printStackTrace();
		}

		//��������ڿͻ��������˵����ӽ���֮��������
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			for(int i=0;i<100;i++){
				ByteBuf reqBuf = Unpooled.buffer(req.length);
				reqBuf.writeBytes(req);
				ctx.writeAndFlush(reqBuf);//���ͻ��������˷��������д��ctx	
			}
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
//			ByteBuf buf = (ByteBuf)msg;
//			
//			byte[] resp = new byte[buf.readableBytes()];
//			buf.readBytes(resp);
//			
//			String rec = new String(resp,"UTF-8");
//			System.out.println("rec:"+rec);
			String rec = (String)msg;//due to the StringDecoder;
			System.out.println("rec:"+rec+" "+counter++);
		}
		
	}
	
	public static void main(String[] args){
		String host = "127.0.0.1";
		int port = 8888;
		
		new NettyClient().connect(host, port);
	}
	
	
	
	
	
	
}
