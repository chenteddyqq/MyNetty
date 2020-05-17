package com.ted.tank.decorator;

import com.ted.tank.GameObject;

import java.awt.*;

public class LineDecorator extends GODecorator {
    public LineDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        //先要画本身的物体后，再画装饰的
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(go.x,go.y,
                go.x+go.getWidth(),
                go.y+ go.getHeight());
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
