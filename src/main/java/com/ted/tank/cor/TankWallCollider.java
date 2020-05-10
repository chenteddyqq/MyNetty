package com.ted.tank.cor;

import com.ted.tank.GameObject;
import com.ted.tank.Tank;
import com.ted.tank.Wall;

public class TankWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall){
            Tank t1 = (Tank)o1;
            Wall t2 = (Wall)o2;
            if (t1.getRect().intersects(t2.rect)){
                t1.back();
            }else if(o1 instanceof Wall && o2 instanceof Tank){
                return collide(o2,o1);
            }
        }
        return true;
    }
}
