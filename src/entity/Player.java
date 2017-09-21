package entity;

import main.KeyHandler;
import main.Screen;

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
        if (input.up.isPressed())
            y--;
        if (input.down.isPressed())
            y++;
        if (input.left.isPressed())
            x--;
        if (input.right.isPressed())
            x++;
    }

    public void render(Screen screen) {

    }

}
