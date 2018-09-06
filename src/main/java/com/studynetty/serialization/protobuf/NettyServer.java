package com.studynetty.serialization.protobuf;

import io.netty.bootstrap.ServerBootstrap;
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
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Date;
import java.util.UUID;

/**
 * 使用netty开发的时间服务器服务端
 * @author Administrator
 *
 */
public class NettyServer {
	public void bind(int port){
		//配置netty服务端的线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();//接收客户端的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();//进行SocketChannel的网络读写
		
		ServerBootstrap bootstrap = new ServerBootstrap();//Nio提供的服务端的辅助启动类
		bootstrap.group(bossGroup,workerGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 1024)
		.childHandler(new ChildChannelHandler());
		
		//绑定端口等待同步成功
		try {
			ChannelFuture f = bootstrap.bind(port).sync();
			System.out.println("server start");
			
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			//释放线程组资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());//处理半包问题
			ch.pipeline().addLast(new ProtobufDecoder(RequestProto.Request.getDefaultInstance()));//告知解码器解码的类的类型
			ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
			ch.pipeline().addLast(new ProtobufEncoder());//添加编码器
			ch.pipeline().addLast(new RequesterverHandler());
		}
		
	}
	
	
	private class  RequesterverHandler extends ChannelHandlerAdapter{
		private int counter;

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			RequestProto.Request request = (RequestProto.Request)msg;
			System.out.println("request:"+request.getRequestContent());
			
			ResponseProto.Response.Builder reBuilder = ResponseProto.Response.newBuilder();
			reBuilder.setResponseId(1);
			reBuilder.setRequestId(request.getRequestId());
			reBuilder.setResponseContent("protobuf response");
			
			ctx.writeAndFlush(reBuilder.build());//由于使用了ProtoBufDecoder所以这里不需要手动编码
		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx)
				throws Exception {
			ctx.flush();//将发送缓冲区的消息写入SocketChannel中
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			//出现异常释放相关资源
			ctx.close();
			cause.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		int port = 8888;
		
		new NettyServer().bind(port);
	}
	
	
	
	
	
	

}
