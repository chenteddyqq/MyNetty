package com.ted.movingsnake;

import com.ted.tank.Group;
import com.ted.tank.Tank;
import com.ted.tank.TankFrame;

import java.util.Random;

public class TMain {

    public static void main(String[] args) throws InterruptedException {
//
        SnakeFrame t = new SnakeFrame();

        for (int i=0;i<5;i++){
            t.blocks.add(new RectBlock(
                    new Random().nextInt(SnakeFrame.GAME_WIDTH-50),
                    new Random().nextInt(SnakeFrame.GAME_HEIGHT-50)
            ));
        }

        while (true){
            Thread.sleep(50);
            t.repaint();
        }
    }
}
