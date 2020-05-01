package cn.ted.netty.sixthexam;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;
        if (0==randomInt){
            MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                    .setName("Zhang三")
                    .setAge(18)
                    .setAddress("万国大天朝")
                    .build();
            myMessage= MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(person)
                    .build();

        }else if (1==randomInt){
            myMessage= MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder()
                            .setName("小黑")
                            .setAge(10)
                            .build())
                    .build();
        }else{
            myMessage= MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder()
                            .setName("咪咪").setCity("深圳")
                            .build())
                    .build();
        }

        ctx.writeAndFlush(myMessage);

    }
}
