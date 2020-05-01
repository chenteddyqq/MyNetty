package cn.ted.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();

            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress(8899));

            while (true){
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();

                for (SelectionKey selectionKey:keySet){
                    if (selectionKey.isConnectable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        if (client.isConnectionPending()){
                            client.finishConnect();
                            ByteBuffer writebuffer = ByteBuffer.allocate(1024);
                            writebuffer.put((LocalDateTime.now()+" 连接成功").getBytes());
                            writebuffer.flip();
                            client.write(writebuffer);

                            ExecutorService executorService =
                                    Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(()->{
                               while (true){
                                   try {
                                       writebuffer.clear();
                                       InputStreamReader input = new InputStreamReader(System.in);
                                       BufferedReader br =new BufferedReader(input);
                                       String message = br.readLine();
                                       writebuffer.put(message.getBytes());
                                       writebuffer.flip();
                                       client.write(writebuffer);
                                   } catch (Exception e) {
                                       e.printStackTrace();
                                   }
                               }
                            });
                        }
                        client.register(selector,SelectionKey.OP_READ);
                    }else if (selectionKey.isReadable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        Charset charset = Charset.forName("utf-8");
                        int count = client.read(readBuffer);
                        if (count>0){
                            readBuffer.flip();
                            String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
                            System.out.println(receivedMessage);
                        }
                    }

                }
                keySet.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
