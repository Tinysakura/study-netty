package com.studynetty.FixedLengthFrameDecoder;

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
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

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
			ch.pipeline().addLast(new FixedLengthFrameDecoder(10));//�̶���20���ֽڽ��н�ȡ
			ch.pipeline().addLast(new StringDecoder());
			ch.pipeline().addLast(new EchoSeverHandler());
		}
		
	}
	
	
	private class EchoSeverHandler extends ChannelHandlerAdapter{
		private int counter;

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			String rec = (String)msg;
			
			System.out.println("rec:"+rec+" "+counter++);
			
			String echo = rec;
			
			ByteBuf resp = Unpooled.copiedBuffer(echo.getBytes());
			
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
