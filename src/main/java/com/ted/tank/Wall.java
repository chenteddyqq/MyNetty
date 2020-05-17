package com.ted.tank;

import java.awt.*;

public class Wall extends GameObject {

    int w, h;
    public Rectangle rect;

    public Wall(int w, int h,int x,int y) {
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(x,y,w,h);
        GameModel.add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,w,h);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }
}
