package com.ted.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    int x;
    int y;
    Dir dir = Dir.DOWN;
    public static  final int WIDTH = ResourceLoader.goodtankU.getWidth();
    public static  final int HEIGHT = ResourceLoader.goodtankU.getHeight();
    Rectangle rect = new Rectangle();
    private static final int SPEED = 5;
    private boolean moving = false;
    private TankFrame tf = null; //持有引用
    public boolean live = true;
    private Group group = Group.BAD;
    private boolean flip = true;
    private Random random = new Random();

    public Tank(int x, int y, Dir dir,Group group, TankFrame f) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = f;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = Tank.WIDTH;
        rect.height = Tank.HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!live) return;

        switch (dir) {
            case LEFT:
                g.drawImage(group == Group.BAD ? ResourceLoader.badtankL1 : ResourceLoader.goodtankL1, x, y, null);
                break;
            case RIGHT:
                g.drawImage(group == Group.BAD ? ResourceLoader.badtankR1 : ResourceLoader.goodtankR1, x, y, null);
                break;
            case UP:
                g.drawImage(group == Group.BAD ? ResourceLoader.badtankU1 : ResourceLoader.goodtankU1, x, y, null);
                break;
            case DOWN:
                g.drawImage(group == Group.BAD ? ResourceLoader.badtankD1 : ResourceLoader.goodtankD1, x, y, null);
                break;
            default:
                break;
        }


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
        rect.x = x;
        rect.y = y;
        if(this.group ==Group.BAD && random.nextInt(10)>8){
            fire();
            randomDir();
        }
        //做个边界检测 有新功能的时候，写个新的方法，不要把所有的功能都放在一起。
        boundsCheck();
    }

    private void boundsCheck() {
        if(this.x<0) x = 0;
        if(this.y<30) y = 30;
        if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH) x = TankFrame.GAME_WIDTH-Tank.WIDTH;
        if(this.y> TankFrame.GAME_HEIGHT-Tank.HEIGHT) y = TankFrame.GAME_HEIGHT-Tank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x+Tank.WIDTH/2-Bullet.WIDTH/2,
                this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2,this.dir,this.group, this.tf));
    }

    public void die() {
        this.live = false;
    }
}
