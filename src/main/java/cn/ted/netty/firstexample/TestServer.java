package cn.ted.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        //可以就理解成死循环
        EventLoopGroup bossGroup =new NioEventLoopGroup();//接受链接转给worker
        EventLoopGroup workerGroup =new NioEventLoopGroup();//处理后返回给客户

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();//用来启动
            //定义组之后，实际是用反射来创建的
            serverBootstrap.group(bossGroup,workerGroup).channel(
                    NioServerSocketChannel.class).childHandler(new TestServerInitializer());//可以自己编写的处理器处理
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
