package com.ted.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        for (int i=0;i<5;i++) {
            int dirNum = new Random().nextInt(4);
            Dir dir = null;
            switch (dirNum){
                case 0:
                    dir = Dir.LEFT;
                    break;
                case 1:
                    dir = Dir.RIGHT;
                    break;
                case 2:
                    dir = Dir.UP;
                    break;
                default:
                    dir = Dir.DOWN;
                    break;
            }
            Tank enemyTank = new Tank(
                    new Random().nextInt(TankFrame.GAME_WIDTH-50),
                    new Random().nextInt(TankFrame.GAME_HEIGHT-50),
                    dir,Group.BAD, t);
            enemyTank.setMoving(true);
            t.enemyTanks.add(enemyTank);
        }

        while (true){
            Thread.sleep(50);
            t.repaint();
        }
    }
}
