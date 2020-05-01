package cn.ted.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NioServer {

    private static Map<String,SocketChannel> map = new HashMap<String,SocketChannel>();
    public static void main(String[] args) throws Exception {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket serverSocket = ssc.socket();
        serverSocket.bind(new InetSocketAddress(8899));
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select();
            Set<SelectionKey> set = selector.selectedKeys();
            set.forEach(selectionkey->{
                SocketChannel socketChannel =null;
                if (selectionkey.isAcceptable()){
                    ServerSocketChannel ssChannel = (ServerSocketChannel) selectionkey.channel();
                    try {
                        socketChannel = ssChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        String key = UUID.randomUUID().toString();
                        map.put(key,socketChannel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(selectionkey.isReadable()){
                    socketChannel = (SocketChannel) selectionkey.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(450);
                    Charset charset = Charset.forName("utf-8");
                    try {
                        if (0<socketChannel.read(readBuffer)){
                            readBuffer.flip();
                            String receivedmsg= String.valueOf(charset.decode(readBuffer).array());
                            System.out.println(socketChannel+":"+receivedmsg);

                            String senderKey = null;
                            for (Map.Entry<String,SocketChannel> entry : map.entrySet()){
                                if(socketChannel == entry.getValue()){
                                    senderKey = entry.getKey();
                                    break;
                                }
                            }
                            for (Map.Entry<String,SocketChannel> entry : map.entrySet()){
                                SocketChannel sendsc = entry.getValue();
                                ByteBuffer writeBuffer = ByteBuffer.allocate(450);
                                writeBuffer.put(("【"+senderKey+"】:"+receivedmsg).getBytes());
                                writeBuffer.flip();
                                sendsc.write(writeBuffer);
                            }

                        }

                    } catch (Exception e) {

                    }

                }

            });
            set.clear();
        }

    }
}
