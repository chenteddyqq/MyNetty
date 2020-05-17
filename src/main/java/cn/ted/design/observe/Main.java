package cn.ted.design.observe;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Child c = new Child();
        c.wakeUp();
    }

}

class Child {
    private boolean cry = false;
    private List<Observer> observers = new ArrayList<>();
    {
        observers.add(new Dad());
        observers.add(new Mom());
    }
    public boolean isCry(){return cry;}
    public void wakeUp(){
        cry = true;
        WakeUpEvent e = new WakeUpEvent(System.currentTimeMillis(),"bed",this);
        for (Observer o:observers){
            o.actionOnWakeUp(e);
        }
    }

}

abstract class Event<T>{
    abstract T getSource();
}

//定义一个事件
class WakeUpEvent extends Event<Child>{

    long timeStamp;
    String loc;
    Child source;

    @Override
    Child getSource() {
        return null;
    }
    public WakeUpEvent(long timeStamp, String loc, Child source) {
        this.timeStamp = timeStamp;
        this.loc = loc;
        this.source = source;
    }
}


interface Observer{
    void actionOnWakeUp(WakeUpEvent event);
}

class Dad implements Observer{

    public void feed(){
        System.out.println("DAD is Feeding");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        feed();
        System.out.println(event.timeStamp);
    }
}
class Mom implements Observer{

    public void hug(){
        System.out.println("mom is hugging");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        hug();
    }
}
