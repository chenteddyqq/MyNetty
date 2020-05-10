package com.ted.tank;

import com.ted.tank.cor.ColliderChain;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    static final int GAME_WIDTH=800, GAME_HEIGHT=600;
    Tank mainTank = new Tank(200,200,Dir.DOWN,Group.GOOD);
//    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> enemyTanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    static List<GameObject> objects = new ArrayList<>();

    public static  GameModel getInstance(){
        return INSTANCE;
    }

    static {
        init();
    }

    private GameModel(){}

    private static void init(){ //这样做是为了避免两个new constructor里面相互调
        int initialTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i=0;i<initialTankCount;i++) {

            Dir dir = Dir.values()[new Random().nextInt(4)];

            Tank enemyTank = new Tank(
                    new Random().nextInt(GameModel.GAME_WIDTH-50),
                    new Random().nextInt(GameModel.GAME_HEIGHT-50),
                    dir,Group.BAD);
            enemyTank.setMoving(true);
            add(enemyTank);
            //初始化墙
        }
        new Wall(150,50,50,90);
        new Wall(250,50,300,100);
        new Wall(50,150,400,200);
        new Wall(20,100,500,400);
    }

    public static void add(GameObject go){
        objects.add(go);
    }

    public void remove(GameObject go){
        objects.remove(go);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("数量是:"+objects.size(),20,40);
        //g.drawString("是:"+objects.get(23),20,55);
        //g.drawString("爆炸的数量是:"+objects.size(),20,70);
        g.setColor(c);


        mainTank.paint(g);
        //b.paint(g);
        for (int i=0;i<objects.size();i++){
            objects.get(i).paint(g);
        }
        //for(int i=0;i<enemyTanks.size();i++){
        //    enemyTanks.get(i).paint(g);
        //}这样画只要一个就全画了
        //paint的画图是把Frame全部清空一遍的

        //碰撞检测 这里用for 报错，原因不明
        /*for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
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
        }*/
        for (int i=0;i<objects.size();i++){
            for (int j=i+1;j<objects.size();j++){
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1,o2);
            }
        }
        //啥时候会有爆炸，碰撞检测的时候
        /*for (Iterator<Explode> it=explodes.iterator();it.hasNext();){
            Explode in = it.next();
            if (!in.live){
                it.remove();
            }else
                in.paint(g);
        }*/
    }

    public Tank getMainTank() {
        return mainTank;
    }

    //相互碰撞的逻辑
}
