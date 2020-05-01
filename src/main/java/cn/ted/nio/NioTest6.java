package cn.ted.nio;

import java.nio.ByteBuffer;

public class NioTest6 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i=0;i<buffer.capacity();++i){
            buffer.put((byte) i);
        }
        buffer.position(2);
        buffer.limit(6);

        ByteBuffer sliceBuffer = buffer.slice();
        for (int i=0;i<sliceBuffer.capacity();i++){
            byte r = sliceBuffer.get(i);
            r *=2;
            sliceBuffer.put(i,r);
        }

        buffer.clear();
        while (buffer.remaining()>0){
            System.out.println(buffer.get());
        }
    }
}
