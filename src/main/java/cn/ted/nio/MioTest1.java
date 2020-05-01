package cn.ted.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class MioTest1 {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i=0;i<buffer.capacity();++i){
            int ranNumber = new SecureRandom().nextInt(20);
            buffer.put(ranNumber);
        }
        int count =0;
        buffer.flip();
        while (buffer.hasRemaining()){

            System.out.println(buffer.get());

        }
    }
}
