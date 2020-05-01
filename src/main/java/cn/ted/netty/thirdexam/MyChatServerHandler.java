package cn.ted.netty.thirdexam;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.forEach(ch -> {
			if(ch!=channel) {
				ch.writeAndFlush(channel.remoteAddress()+"发送的消息："+msg);
			}else {
				ch.writeAndFlush("[自己] "+ msg);
			}
		});
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		//服务器端要实现广播，就必须把客户端都保存起来
		channelGroup.writeAndFlush("[服务器] -  "+channel.remoteAddress()+" joins");
		channelGroup.add(channel);
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		//服务器端要实现广播，就必须把客户端都保存起来
		channelGroup.writeAndFlush("[服务器] -  ,"+channel.remoteAddress()+" leaves");
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress()+" 上线");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress()+" 下线");
	}
}
