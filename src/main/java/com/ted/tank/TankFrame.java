package com.ted.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {
    static final int GAME_WIDTH=800, GAME_HEIGHT=600;
    Tank mainTank = new Tank(200,200,Dir.DOWN,Group.GOOD,this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> enemyTanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public TankFrame() throws HeadlessException {

        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);

        addKeyListener(new MyKeyLister());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
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

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);//paint上原来直接画在页面上的所有
        g.drawImage(offScreenImage,0,0,null);
    }

    class MyKeyLister extends KeyAdapter{

        boolean bL =false;
        boolean bR =false;
        boolean bU =false;
        boolean bD =false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    mainTank.fire();
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bR && !bU && !bD) mainTank.setMoving(false);
            else {
                mainTank.setMoving(true);
                if (bL) mainTank.setDir(Dir.LEFT);
                if (bR) mainTank.setDir(Dir.RIGHT);
                if (bU) mainTank.setDir(Dir.UP);
                if (bD) mainTank.setDir(Dir.DOWN);
            }
        }
    }
}
