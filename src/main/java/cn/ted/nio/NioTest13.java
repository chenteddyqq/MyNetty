package cn.ted.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest13 {

    public static void main(String[] args) throws Exception {
        String inputFile = "NioTest13_in.txt";
        String outputFile = "NioTest13_out.txt";
        RandomAccessFile infile = new RandomAccessFile(inputFile,"r");
        RandomAccessFile outfile = new RandomAccessFile(outputFile,"rw");
        long fileLength = new File(inputFile).length();
        FileChannel infileChannel = infile.getChannel();
        FileChannel outfileChannel = outfile.getChannel();
        //创建一个内存映射文件，从而内存修改，文件就都修改了
        MappedByteBuffer mappedByteBuffer = infileChannel
                .map(FileChannel.MapMode.READ_ONLY,0,fileLength);
        Charset charset = Charset.forName("gbk");
        Charset charset1 = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset1.newEncoder();
        CharBuffer charBuffer = decoder.decode(mappedByteBuffer);
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        outfileChannel.write(byteBuffer);
        infile.close();
        outfile.close();
    }
}
