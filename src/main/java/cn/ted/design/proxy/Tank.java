package cn.ted.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

public class Tank implements Movable {

    //想记录了坦克开了多长的时间
    @Override
    public void move(String sss) {
        System.out.println("Tank is moving clacla.... \\" + sss);
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tank() {
    }

    public static void main(String[] args) {
        //这就是静态的代理
        //new LongProxy(new TimeProxy(new Tank())).move();
        //动态代理，就是反射
        Tank t = new Tank();
        Movable m =(Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
                new Class[]{Movable.class},
                (Object proxy, Method method, Object[] objs)->{
                    System.out.println("日志开始");
                    Object o = method.invoke(t,objs);
                    System.out.println("日志结束");
                    return o;
                }
                );
        m.move("lalalala");
    }
}


interface Movable{
    void move(String sss);
}

class TimeProxy implements Movable{
    Movable source;

    public TimeProxy(Movable source) {
        this.source = source;
    }

    @Override
    public void move(String sss) {
        long stime = System.currentTimeMillis();
        source.move("ttt");
        float t = (System.currentTimeMillis()-stime)/1000;
        System.out.println("消耗的时间是"+t);
    }
}

class LongProxy implements Movable{
    Movable source;

    public LongProxy(Movable source) {
        this.source = source;
    }

    @Override
    public void move(String sss) {
        System.out.println("日志开始");
        source.move("oooo...");
        System.out.println("日志结束" + sss);
    }
}
