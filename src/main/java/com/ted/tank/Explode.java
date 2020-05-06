package com.ted.tank;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Explode {
    int x;
    int y;
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
            die();
            try {
                InputStream in = new FileInputStream("audio/explode.wav");
                AudioStream as = new AudioStream(in);
                AudioPlayer.player.start(as);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void die(){
        this.live = false;
    }
}
