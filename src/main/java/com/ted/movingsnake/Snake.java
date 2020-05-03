package com.ted.movingsnake;


import java.awt.*;

public class Snake {

    private int x,y;
    private final int SPEED = 5;
    private Dir dir = Dir.DOWN;
    private final int WIDTH = 50,
            HEIGHT = 50;
    private SnakeFrame tf = null;
    public Rectangle rectangle = null;

    int tailRectnum = 1;
    public int[] xPos = new int[tailRectnum*10];
    public int[] yPos = new int[tailRectnum*10];
    int step =tailRectnum*10-1;

    public Snake(int x, int y, Dir dir, SnakeFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        rectangle = new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public void paint(Graphics g) {
        rectangle.setLocation(x,y);
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        if (step>=0) {
            xPos[step] = x;
            yPos[step] = y;
            step--;
        }
        if (step<=-1) {
            for (int i=xPos.length-1;i>=1;i--){
                xPos[i]=xPos[i-1];
                yPos[i]=yPos[i-1];
            }
            step =0;
        };
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

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public void grow(){
        this.tailRectnum++;
        int[] tmpx = xPos;
        int[] tmpy = yPos;
        xPos = new int[tailRectnum*10];
        yPos = new int[tailRectnum*10];
        for (int i=0;i<tmpx.length;i++){
            xPos[i]=tmpx[i];
            yPos[i]=tmpy[i];
        }
        int index = tmpx.length-1;
        for(int i = tmpx.length;i<tailRectnum*10;i++){
                xPos[i]=tmpx[index];
                yPos[i]=tmpy[index];
        }
    }
}
