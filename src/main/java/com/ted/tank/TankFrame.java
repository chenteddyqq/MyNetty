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

    GameModel gm = new GameModel();

    public TankFrame() throws HeadlessException {

        setSize(GameModel.GAME_WIDTH, GameModel.GAME_HEIGHT);
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

        gm.paint(g);


    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GameModel.GAME_WIDTH,GameModel.GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GameModel.GAME_WIDTH,GameModel.GAME_HEIGHT);
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
                    gm.getMainTank().fire(FourFireDirection.getInstance());
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bR && !bU && !bD) gm.getMainTank().setMoving(false);
            else {
                gm.getMainTank().setMoving(true);
                if (bL) gm.getMainTank().setDir(Dir.LEFT);
                if (bR) gm.getMainTank().setDir(Dir.RIGHT);
                if (bU) gm.getMainTank().setDir(Dir.UP);
                if (bD) gm.getMainTank().setDir(Dir.DOWN);
            }
        }
    }
}
