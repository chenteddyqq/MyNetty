package com.ted.movingsnake;

import com.ted.movingsnake.Dir;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SnakeFrame extends Frame {

    static final int GAME_WIDTH=800, GAME_HEIGHT=600;

    Snake snake = new Snake(50,50, Dir.DOWN,this);
    List<RectBlock> blocks = new ArrayList<>();

    public SnakeFrame() throws HeadlessException {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("贪吃蛇");
        setVisible(true);
        addKeyListener(new MyKeyLister());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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

    @Override
    public void paint(Graphics g) {
        snake.paint(g);
        for (int i=0;i<snake.xPos.length;i++){
            Color c = g.getColor();
            g.setColor(Color.blue);
            g.fillRect(snake.xPos[i],snake.yPos[i],50,50);
            g.setColor(c);
        }
        for (Iterator<RectBlock> it = blocks.iterator(); it.hasNext();){
            RectBlock b = it.next();
            if (!b.live) it.remove();
            b.paint(g);
        }
        for (RectBlock b : blocks) {
            b.collideWith(snake);
        }
    }

    class MyKeyLister extends KeyAdapter {

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
//                case KeyEvent.VK_SPACE:
//                    snake.grow();
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {

            if (bL) snake.setDir(Dir.LEFT);
            if (bR) snake.setDir(Dir.RIGHT);
            if (bU) snake.setDir(Dir.UP);
            if (bD) snake.setDir(Dir.DOWN);

        }
    }
}
