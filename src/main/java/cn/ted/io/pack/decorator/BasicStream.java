package cn.ted.io.pack.decorator;

public class BasicStream implements BasicStreamIface {

    public void doSomething(){
        System.out.println("The is AAA basic method!!");
    }

    public static void main(String[] args) {
        BasicStream bs =new BasicStream();

        basicDecorator b = new Decorator2(bs);

        b.doSomething();
    }
}
