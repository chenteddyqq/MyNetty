package com.ted.tank;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

public class TMain {

    public static void main(String[] args) throws InterruptedException {
//        Frame f = new Frame();
//        f.setSize(800,600);
//        f.setResizable(false);
//        f.setTitle("坦克大战");
//        f.setVisible(true);
//
//        f.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
        TankFrame t = new TankFrame();
        int initialTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i=0;i<initialTankCount;i++) {

            Dir dir = Dir.values()[new Random().nextInt(4)];

            Tank enemyTank = new Tank(
                    new Random().nextInt(TankFrame.GAME_WIDTH-50),
                    new Random().nextInt(TankFrame.GAME_HEIGHT-50),
                    dir,Group.BAD, t);
            enemyTank.setMoving(true);
            t.enemyTanks.add(enemyTank);
        }

        new Thread(()->{
            try {
                InputStream in = new FileInputStream("audio/war1.wav");
                AudioStream as = new AudioStream(in);
                while (true) {
                    as.mark(123456);
                    AudioPlayer.player.start(as);
                    while (as.available()>0);
                    as.reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        while (true){
            Thread.sleep(50);
            t.repaint();
        }
    }
}
