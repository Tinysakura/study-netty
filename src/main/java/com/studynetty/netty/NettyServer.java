package com.studynetty.netty;

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

import java.util.Date;

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
			//���Զ����Handler����������
			ch.pipeline().addLast(new TimeServerHandler());
		}
		
	}
	
	
	private class TimeServerHandler extends ChannelHandlerAdapter{

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			//ByteBuf������nio���е�ByteBuffer
			ByteBuf buf = (ByteBuf)msg;
			
			byte[] req = new byte[buf.readableBytes()];
			buf.readBytes(req);//���ֽڶ����ֽ�����
			String rec = new String(req,"UTF-8");
			
			System.out.println("rec:"+rec);
			
			String currentTime = "QUERY_TIME".equalsIgnoreCase(rec)?new Date(System.currentTimeMillis()).toString():"BAD_QUERY";
			
			ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
			
			ctx.write(resp);//����Ӧ���д��ctx
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