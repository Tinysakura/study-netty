package com.studynetty.netty;

import java.security.acl.Group;

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

/**
 * 基于netty的时间客户端开发
 * @author Administrator
 *
 */
public class NettyClient {
	
	public void connect(String host,int port){
		EventLoopGroup group = new NioEventLoopGroup();//创建客户端的线程组
		
		try {		
			Bootstrap bootstrap = new Bootstrap();
			
			bootstrap.group(group).channel(NioSocketChannel.class)
			    .option(ChannelOption.TCP_NODELAY, true)
			    .handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new TimeClientHandler());
					}
				});
			
			//发起异步连接操作
			ChannelFuture f = bootstrap.connect(host, port);
			System.out.println("client connected");
			
			//等待客户端链路关闭
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}
	
	private class TimeClientHandler extends ChannelHandlerAdapter{
		ByteBuf reqBuf;
		
		public TimeClientHandler(){
			byte[] req = "QUERY_TIME".getBytes();
			reqBuf = Unpooled.copiedBuffer(req);
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			ctx.close();
			cause.printStackTrace();
		}

		//这个方法在客户端与服务端的连接建立之初被调用
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			ctx.writeAndFlush(reqBuf);//将客户端向服务端发起的请求写入ctx
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			ByteBuf buf = (ByteBuf)msg;
			
			byte[] resp = new byte[buf.readableBytes()];
			buf.readBytes(resp);
			
			String rec = new String(resp,"UTF-8");
			System.out.println("rec:"+rec);
		}
		
	}
	
	public static void main(String[] args){
		String host = "127.0.0.1";
		int port = 8888;
		
		new NettyClient().connect(host, port);
	}
	
	
	
	
	
	
}
