package com.ted.tank;

import java.awt.*;

public class Explode {
    int x;
    int y;
    public static  final int WIDTH = ResourceLoader.explodes[0].getWidth();
    public static  final int HEIGHT = ResourceLoader.explodes[0].getHeight();
    private TankFrame tf = null; //持有引用
    public boolean live = true;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        //new Audio("audio/war1.wav").play();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceLoader.explodes[step++],x,y,null);
        if(step >= ResourceLoader.explodes.length) {
            step=0;
            die();
        }
    }

    public void die(){
        this.live = false;
    }
}
