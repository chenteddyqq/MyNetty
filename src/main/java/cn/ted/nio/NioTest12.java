package cn.ted.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest12 {

    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];

        ports[0]=5000;
        ports[1]=5001;
        ports[2]=5002;
        ports[3]=5003;
        ports[4]=5004;
        //selector的编写
        Selector selector=Selector.open();
        for (int port : ports) {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ServerSocket serverSocket = ssc.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            serverSocket.bind(address);
            //channel注册上selector，感兴趣的事件是ACCEPT
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口 " + port);
        }
        while (true){
            int number = selector.select();
            System.out.println("event number: "+number);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys:"+selectionKeys);
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()){
                SelectionKey selectionKey = iter.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    iter.remove();
                    System.out.println("获得客户端的连接:"+socketChannel);
                }
                if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    int bytesRead = 0;
                    ByteBuffer byteBuffer = ByteBuffer.allocate(8);
                    while (0 < socketChannel.read(byteBuffer)){
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        byteBuffer.clear();
                    }
                    System.out.println("byteRead : end");
                    iter.remove();
                }
            }
        }

    }
}
