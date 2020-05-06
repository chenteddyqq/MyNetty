package com.ted.tank;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.BufferedInputStream;
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


        new Thread(()->{
            new Audio("audio/war1.wav").loop();
        }).start();

        while (true){
            Thread.sleep(50);
            t.repaint();
        }
    }
}
