package cn.ted.design.proxy.JDKProxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
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

    @Override
    public void calls() {
        System.out.println("call Tank s");
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
        m.calls();
        JdkProxySourceClass.writeClassToDisk("/Users/chenteddy/Desktop/cn/$Proxy0.class");
    }
}


interface Movable{
    void move(String sss);
    void calls();
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

    @Override
    public void calls() {
        System.out.println("call Time s");
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

    @Override
    public void calls() {
        System.out.println("call Long s");
    }
}

class JdkProxySourceClass {

    public static void writeClassToDisk(String path) {
        //其实真正生成代理的就是这句话
        byte[] classFile = ProxyGenerator.generateProxyClass("$proxy0", new Class[]{Movable.class});
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}