package cn.ted.io.pack.decorator;

public class basicDecorator implements BasicStreamIface {

    private BasicStreamIface in;

    public basicDecorator(BasicStreamIface in){
        this.in=in;
    }

    @Override
    public void doSomething() {
        in.doSomething();
    }
}
