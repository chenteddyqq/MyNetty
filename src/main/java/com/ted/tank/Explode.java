package com.ted.tank;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Explode extends GameObject {

    public static  final int WIDTH = ResourceLoader.explodes[0].getWidth();
    public static  final int HEIGHT = ResourceLoader.explodes[0].getHeight();
    GameModel gm = null; //持有引用
    public boolean live = true;
    private int step = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;

        //new Audio("audio/war1.wav").play();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceLoader.explodes[step++],x,y,null);
        if(step >= ResourceLoader.explodes.length) {
            step=0;
            gm.remove(this);
            die();
            try {
                new Audio("audio/explode.wav").play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void die(){
        this.live = false;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
