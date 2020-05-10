package com.ted.tank.cor;

import com.ted.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
