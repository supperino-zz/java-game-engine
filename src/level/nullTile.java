package level;

import main.Screen;
import main.Sprite;

public class nullTile extends Tile {

    public nullTile(Sprite sprite) {
        super(sprite);
        // TODO Auto-generated constructor stub
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
}
