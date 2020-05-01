package cn.ted.io.pack.decorator;

public class Decorator2 extends basicDecorator {

    public Decorator2(BasicStreamIface in){
        super(in);
    }


    private void another(){
        System.out.println("Decorator Two !!!");
    }

    @Override
    public void doSomething() {
        this.another();
        super.doSomething();
    }
}
