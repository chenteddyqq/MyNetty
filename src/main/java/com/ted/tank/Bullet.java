package com.ted.tank;

import java.awt.*;

public class Bullet {

    private int x,y;
    private final int SPEED = 5;
    private Dir dir;
    private final int WIDTH =10, HEIGHT =10;
    private TankFrame tf = null;
    private boolean live = true;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        if(!this.live){
            tf.bullets.remove(this);
        }

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y, WIDTH, HEIGHT);
        g.setColor(c);
        move();

    }

    private void move() {
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            default:
                break;
        }
        if(x<0 || y<0 ||x> TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) live = false;
    }
}
