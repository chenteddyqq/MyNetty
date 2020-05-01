package cn.ted.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufTest {

    public static void main(String[] args) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三")
                .setAge(20)
                .setAddress("北京")
                .build();
        byte[] student2ByteArray = student.toByteArray();

        DataInfo.Student studentReceived = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(studentReceived.getName());
        System.out.println(studentReceived.getAge());
        System.out.println(studentReceived.getAddress());
    }
}
