package com.ted.tank;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;

public class DefaultFireStrategy implements FireStrategy {

    static DefaultFireStrategy f = new DefaultFireStrategy();

    private DefaultFireStrategy(){}

    @Override
    public void fire(Tank t) {
        int bX = t.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY = t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        new Bullet(bX,bY,t.dir,t.group);
        if (t.group == Group.GOOD){
            new Thread(()->{
                try {
                    InputStream in = new FileInputStream("audio/tank_fire.wav");
                    AudioStream as = new AudioStream(in);
                    AudioPlayer.player.start(as);
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
