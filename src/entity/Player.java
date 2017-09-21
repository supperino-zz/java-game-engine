package entity;

import main.KeyHandler;
import main.Screen;
import main.Sprite;

public class Player extends Mob {
    private KeyHandler input;

    public Player(int x, int y) {
        // TODO Auto-generated constructor stub
        this.x = x;
        this.y = y;
    }

    public Player(KeyHandler input) {
        // TODO Auto-generated constructor stub
        this.input = input;
    }

    public void update() {
        /*
         * if (input.up.isPressed()) y--; if (input.down.isPressed()) y++; if
         * (input.left.isPressed()) x--; if (input.right.isPressed()) x++;
         */

        int mPosx = 0, mPosy = 0;
        if (input.up.isPressed())
            mPosy--;
        if (input.down.isPressed())
            mPosy++;
        if (input.left.isPressed())
            mPosx--;
        if (input.right.isPressed())
            mPosx++;

        if (mPosx != 0 || mPosy != 0) {
            move(mPosx, mPosy);
        }

    }

    public void render(Screen screen) {
        screen.renderPlayer(x, y, Sprite.player0);
    }

}
