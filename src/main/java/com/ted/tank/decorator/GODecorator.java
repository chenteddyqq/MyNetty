package com.ted.tank.decorator;

import com.ted.tank.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {

    public GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g);

}
