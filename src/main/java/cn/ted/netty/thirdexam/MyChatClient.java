package cn.ted.netty.thirdexam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyChatClient {

	public static void main(String[] args) {
		
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(
					new MyChatClientInitializer());
			ChannelFuture chanelFuture= bootstrap.connect("localhost",8899).sync();
			Channel channel = chanelFuture.channel();
			//chanelFuture.channel().closeFuture().sync();在主线程里面读键盘的输入和发数据
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				channel.writeAndFlush(br.readLine());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
