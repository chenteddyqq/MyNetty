package cn.ted.io.pack.decorator;

public class Decorator1 extends  basicDecorator {


    public Decorator1(BasicStreamIface in){
        super(in);
    }


    private void another(){
        System.out.println("Decorator One !!!");
    }

    @Override
    public void doSomething() {
        this.another();
        super.doSomething();
    }
}
