package entity;

import main.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;

    // direções que o maluco vai andar
    // 0 /\
    // 1 >
    // 2 \/
    // 3 <
    public void move(int xm, int ym) {
        if (xm > 0)
            dir = 1;
        if (xm < 0)
            dir = 3;
        if (ym > 0)
            dir = 2;
        if (ym > 0)
            dir = 0;

        if (!collision()) {
            x += xm;
            y += ym;
        }
    }

    public void update() {

    }

    private boolean collision() {
        return false;
    }

}
