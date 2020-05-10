package com.ted.tank.cor;

import com.ted.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {

    //implments Collider 这样链条本身也可以chain1.add(chain2)；
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new BulletWallCollider());
        add(new TankWallCollider());
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (Collider tmpc : colliders){
            if(!tmpc.collide(o1,o2)) return false ;
        }
        return true;
    }

    public void add(Collider c){
        colliders.add(c);
    }
}
