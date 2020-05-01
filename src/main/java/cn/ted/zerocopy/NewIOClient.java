package cn.ted.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws Exception {

        String fileName ="/Users/chenteddy/Desktop/Intelij_Idea/ideaIU-2019.3.3.dmg";
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发的总字节:"+transferCount+",耗时："+(System.currentTimeMillis()-startTime));
        fileChannel.close();
    }
}
