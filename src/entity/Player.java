package entity;

import main.KeyHandler;
import main.Screen;
import main.Sprite;

public class Player extends Mob {
    private KeyHandler input;
    private int timer_sprite;

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
        } else {
            moving = false;
        }
        

        timer_sprite++;
        if (timer_sprite < 10_000) {
            timer_sprite ++;
        }

    }

    public void render(Screen screen) {
        // screen.renderPlayer(x, y, Sprite.player[0]);
        if (dir == 0) {
            if (moving) {
                if(timer_sprite % 20 > 10) {
                    screen.renderPlayer(x, y, Sprite.player_foward[1]);
                }else {
                    screen.renderPlayer(x, y, Sprite.player_foward[2]);
                }
            }else {
                screen.renderPlayer(x, y, Sprite.player_foward[0]);
            }
        }
        if (dir == 1) {
            if (moving) {
                if(timer_sprite % 20 > 10) {
                    screen.renderPlayer(x, y, Sprite.player_right[1]);
                }else {
                    screen.renderPlayer(x, y, Sprite.player_right[2]);
                }
            }else {
                screen.renderPlayer(x, y, Sprite.player_right[0]);
            }
        }
        if (dir == 2) {
            if (moving) {
                if(timer_sprite % 20 > 10) {
                    screen.renderPlayer(x, y, Sprite.player_backwards[1]);
                }else {
                    screen.renderPlayer(x, y, Sprite.player_backwards[2]);
                }
            }else {
                screen.renderPlayer(x, y, Sprite.player_backwards[0]);
            }
        }
        if (dir == 3) {
            if (moving) {
                if(timer_sprite % 20 > 10) {
                    screen.renderPlayer(x, y, Sprite.player_left[1]);
                }else {
                    screen.renderPlayer(x, y, Sprite.player_left[2]);
                }
            }else {
                screen.renderPlayer(x, y, Sprite.player_left[0]);
            }
        }
    }

}
