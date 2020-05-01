package cn.ted.netty.fifthwebsocketchat;

import java.time.LocalDateTime;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

public class TextWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	//TextWebSocketFrame 专门用来处理文本帧的
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		//System.out.println("received: "+msg.text());
		//ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器的时间是: "+LocalDateTime.now()));

		Channel channel = ctx.channel();
		channelGroup.forEach(ch -> {
			if(ch!=channel) {
				ch.writeAndFlush(new TextWebSocketFrame(channel.remoteAddress()+"发送的消息："+msg.text()));
			}else {
				ch.writeAndFlush(new TextWebSocketFrame("[自己] "+ msg.text()));
			}
		});
		System.out.println(channelGroup.size());
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("handlerAdded:"+ctx.channel().id().asLongText());
		Channel channel = ctx.channel();
		//服务器端要实现广播，就必须把客户端都保存起来
		channelGroup.writeAndFlush(new TextWebSocketFrame("[服务器] - "+channel.remoteAddress()+" 加入啦"));
		channelGroup.add(channel);
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("handlerRemoved: "+ctx.channel().id().asLongText());
		Channel channel = ctx.channel();
		//服务器端要实现广播，就必须把客户端都保存起来
		channelGroup.writeAndFlush(new TextWebSocketFrame("[服务器] - "+channel.remoteAddress()+" 断线啦"));
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("exception occured");
		ctx.close();
	}
}
