package com.ted.tank;

import com.ted.tank.decorator.LineDecorator;
import com.ted.tank.decorator.RectDecorator;


import java.io.FileInputStream;
import java.io.InputStream;

public class DefaultFireStrategy implements FireStrategy {

    static DefaultFireStrategy f = new DefaultFireStrategy();

    private DefaultFireStrategy(){}

    @Override
    public void fire(Tank t) {
        int bX = t.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY = t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        //new Bullet(bX,bY,t.dir,t.group);
        GameModel.getInstance().add(
                new RectDecorator(
                        new LineDecorator(
                        new Bullet(bX,bY,t.dir,t.group))));
        if (t.group == Group.GOOD){
            new Thread(()->{
                try {
                    new Audio("audio/tank_fire.wav").play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static DefaultFireStrategy getInstance(){
        return f;
    }
}
