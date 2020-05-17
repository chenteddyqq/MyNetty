package cn.ted.design.proxy.CGlib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Random;

public class Tank {

    //想记录了坦克开了多长的时间

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
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback(new TimeMethodInterceptor());
        Tank t = (Tank) enhancer.create();
        t.move(" clclclclcl");
    }
}
class TimeMethodInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object result = null;
        result = methodProxy.invokeSuper(o,args);
        System.out.println("after...");
        return result;
    }
}




