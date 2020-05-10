package com.ted.tank.cor;

import com.ted.tank.*;
public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;

            if(b.getGroup()== Group.GOOD && b.rect.intersects(t.rect)){
                t.die();
                b.die();
                GameModel.getInstance().add(new Explode(
                        t.x+(Tank.WIDTH-Explode.WIDTH)/2,
                        t.y+(Tank.HEIGHT-Explode.HEIGHT)/2,GameModel.getInstance()));
                return false;
            }
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2,o1);
        }
        return true;

    }
}
