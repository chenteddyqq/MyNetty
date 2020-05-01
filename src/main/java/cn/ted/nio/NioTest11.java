package cn.ted.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioTest11 {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.bind(address);

        int messageLength = 2+3+4;
        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0]=ByteBuffer.allocate(2);
        buffers[1]=ByteBuffer.allocate(3);
        buffers[2]=ByteBuffer.allocate(4);

        SocketChannel socketChannel=serverSocketChannel.accept();
        while (true){
            int bytesread = 0;
            while (bytesread<messageLength){
                long r= socketChannel.read(buffers);
                bytesread += r;
                System.out.println("byteread: "+bytesread+" r:"+r);
                Arrays.asList(buffers).stream()
                        .map(buffer ->"Position: "+buffer.position()+", Limit:"+buffer.limit())
                        .forEach(System.out::println);
            }
            Arrays.asList(buffers).forEach(buffer->{
                buffer.flip();
            });
            long byteWritten =0;
            while (byteWritten<messageLength){
                long r = socketChannel.write(buffers);
                byteWritten += r;
            }
            Arrays.asList(buffers).forEach(buffer->buffer.clear());
            System.out.println("Read: "+bytesread +" Written: "+byteWritten);
        }
    }
}
