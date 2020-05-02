package com.ted.tank;

import java.awt.*;

public class Tank {
    int x;
    int y;
    Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = false;
    private TankFrame tf = null; //持有引用

    public Tank(int x, int y, Dir dir, TankFrame f) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = f;
    }

    public void paint(Graphics g) {
        Color c= g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y, 50, 50);
        g.setColor(c);
        move();
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void move() {
        if (!moving) return;
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
    }


    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x,this.y,this.dir, this.tf));
    }
}
