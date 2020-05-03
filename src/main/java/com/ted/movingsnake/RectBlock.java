package com.ted.movingsnake;

import java.awt.*;

public class RectBlock {
    private int x,y;
    private final int WIDTH = 50,
            HEIGHT = 50;
    private Rectangle rectBlock = null;
    boolean live = true;

    public RectBlock(int x, int y) {
        this.x = x;
        this.y = y;
        this.rectBlock = new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public void die(){
        this.live = false;
    }

    public void paint(Graphics g) {
        if (!live) return;
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(c);
    }

    public void collideWith(Snake snake){

        if(rectBlock.intersects(snake.rectangle)){
            this.die();
            snake.grow();
        }
    }
}
