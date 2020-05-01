package cn.ted.nio;

import java.nio.ByteBuffer;

public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putLong(1000L);
        byteBuffer.putChar('我');
        byteBuffer.putDouble(3.14);
        byteBuffer.flip();
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getDouble());
    }
}
