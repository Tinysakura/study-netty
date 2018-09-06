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
 * ʹ��netty������ʱ������������
 * @author Administrator
 *
 */
public class NettyServer {
	public void bind(int port){
		//����netty����˵��߳���
		EventLoopGroup bossGroup = new NioEventLoopGroup();//���տͻ��˵�����
		EventLoopGroup workerGroup = new NioEventLoopGroup();//����SocketChannel�������д
		
		ServerBootstrap bootstrap = new ServerBootstrap();//Nio�ṩ�ķ���˵ĸ���������
		bootstrap.group(bossGroup,workerGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 1024)
		.childHandler(new ChildChannelHandler());
		
		//�󶨶˿ڵȴ�ͬ���ɹ�
		try {
			ChannelFuture f = bootstrap.bind(port).sync();
			System.out.println("server start");
			
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			//�ͷ��߳�����Դ
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());//����������
			ch.pipeline().addLast(new ProtobufDecoder(RequestProto.Request.getDefaultInstance()));//��֪������������������
			ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
			ch.pipeline().addLast(new ProtobufEncoder());//��ӱ�����
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
			
			ctx.writeAndFlush(reBuilder.build());//����ʹ����ProtoBufDecoder�������ﲻ��Ҫ�ֶ�����
		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx)
				throws Exception {
			ctx.flush();//�����ͻ���������Ϣд��SocketChannel��
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			//�����쳣�ͷ������Դ
			ctx.close();
			cause.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		int port = 8888;
		
		new NettyServer().bind(port);
	}
	
	
	
	
	
	

}
