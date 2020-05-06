package com.ted.tank;

import java.awt.*;

public class Bullet {

    private int x,y;
    private final int SPEED = 15;
    private Dir dir;
    public static final int WIDTH =ResourceLoader.bulletL.getWidth(),
            HEIGHT =ResourceLoader.bulletL.getHeight();
    private GameModel gm = null;
    public boolean live = true;
    Rectangle rect = new Rectangle();
    private Group group = Group.BAD;

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = Bullet.WIDTH;
        rect.height = Bullet.HEIGHT;
        gm.bullets.add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!live) return;
        switch (dir){
            case LEFT:
                g.drawImage(ResourceLoader.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceLoader.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceLoader.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceLoader.bulletD,x,y,null);
                break;
            default:
                break;
        }
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
        rect.x = x;
        rect.y = y;
        if(x<0 || y<0 ||x> GameModel.GAME_WIDTH || y>GameModel.GAME_HEIGHT) this.die();
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;
        //TODO：用一个rect来记录子弹的位置，不能new太多

        if(this.rect.intersects(tank.rect)){
            tank.die();
            this.die();
            gm.explodes.add(new Explode(
                    tank.x+(Tank.WIDTH-Explode.WIDTH)/2,
                    tank.y+(Tank.HEIGHT-Explode.HEIGHT)/2,gm));
        }
    }

    private void die() {
        this.live = false;
    }
}
