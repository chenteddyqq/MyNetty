package com.ted.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameModel {

    static final int GAME_WIDTH=800, GAME_HEIGHT=600;
    Tank mainTank = new Tank(200,200,Dir.DOWN,Group.GOOD,this);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> enemyTanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel(){

        int initialTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i=0;i<initialTankCount;i++) {

            Dir dir = Dir.values()[new Random().nextInt(4)];

            Tank enemyTank = new Tank(
                    new Random().nextInt(GameModel.GAME_WIDTH-50),
                    new Random().nextInt(GameModel.GAME_HEIGHT-50),
                    dir,Group.BAD, this);
            enemyTank.setMoving(true);
            enemyTanks.add(enemyTank);
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量是:"+bullets.size(),20,40);
        g.drawString("敌人的数量是:"+enemyTanks.size(),20,55);
        g.drawString("爆炸的数量是:"+explodes.size(),20,70);
        g.setColor(c);

        mainTank.paint(g);
        //b.paint(g);
        for(int i=0;i<bullets.size();i++){

            bullets.get(i).paint(g);
        }
        for(int i=0;i<enemyTanks.size();i++){

            enemyTanks.get(i).paint(g);
        }
        //paint的画图是把Frame全部清空一遍的

        //碰撞检测 这里用for 报错，原因不明
        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
            Bullet b= it.next();
            if(!b.live){
                it.remove();
            }
            for (Iterator<Tank> enemyIt=enemyTanks.iterator();enemyIt.hasNext();){
                Tank enTank = enemyIt.next();
                if (!enTank.live){
                    enemyIt.remove();
                }
                b.collideWith(enTank);
            }
        }
        //啥时候会有爆炸，碰撞检测的时候
        for (Iterator<Explode> it=explodes.iterator();it.hasNext();){
            Explode in = it.next();
            if (!in.live){
                it.remove();
            }else
                in.paint(g);
        }
    }

    public Tank getMainTank() {
        return mainTank;
    }
}
