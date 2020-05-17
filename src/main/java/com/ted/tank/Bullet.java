package com.ted.tank;

import java.awt.*;

public class Bullet extends GameObject {

    private final int SPEED = 15;
    private Dir dir;
    public static final int WIDTH =ResourceLoader.bulletL.getWidth(),
            HEIGHT =ResourceLoader.bulletL.getHeight();
    public boolean live = true;
    public Rectangle rect = new Rectangle();
    private Group group = Group.BAD;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = Bullet.WIDTH;
        rect.height = Bullet.HEIGHT;
        if(this.getGroup()==Group.GOOD)
        GameModel.getInstance().add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!live){
            GameModel.getInstance().remove(this);
            return;
        }
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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

    /*public boolean collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return false;
        //TODO：用一个rect来记录子弹的位置，不能new太多

        if(this.rect.intersects(tank.rect)){
            tank.die();
            this.die();
            gm.add(new Explode(
                    tank.x+(Tank.WIDTH-Explode.WIDTH)/2,
                    tank.y+(Tank.HEIGHT-Explode.HEIGHT)/2,gm));
            return true;
        }
        return false;
    }*/

    public void die() {
        this.live = false;
    }
}
