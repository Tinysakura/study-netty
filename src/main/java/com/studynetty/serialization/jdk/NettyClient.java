package com.studynetty.serialization.jdk;

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
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
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
						ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(
								this.getClass().getClassLoader())));
						ch.pipeline().addLast(new ObjectEncoder());
						ch.pipeline().addLast(new RespClientHandler());
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
	
	private class RespClientHandler extends ChannelHandlerAdapter{
		Request request;
		int counter;
		
		public RespClientHandler(){
			request = new Request();
			request.setRequestId(1);
			request.setRequestContent("this is request");
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
			ctx.writeAndFlush(request);
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			Response response = (Response)msg;
			
			System.out.println("response:"+response.getResponseContent());
		}
		
	}
	
	public static void main(String[] args){
		String host = "127.0.0.1";
		int port = 8888;
		
		new NettyClient().connect(host, port);
	}
	
	
	
	
	
	
}
