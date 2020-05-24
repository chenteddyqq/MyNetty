package com.ted.tank;

public class FourFireDirection implements FireStrategy {

    private static FourFireDirection f = new FourFireDirection();

    private FourFireDirection(){}

    public static FourFireDirection getInstance(){
        return f;
    }

    @Override
    public void fire(Tank t) {
        int bX = t.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY = t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;

        Dir[] ds = Dir.values();
        for (Dir d : ds){
            new Bullet(bX,bY,d,t.group);
        }
        if (t.group == Group.GOOD){
            new Thread(()->{
                try {
                    new Audio("audio/tank_fire.wav").play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
