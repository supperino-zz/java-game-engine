package level;

import main.Screen;
import main.Sprite;

public abstract class Tile {

    protected int x, y;
    public Sprite sprite;
    protected boolean solid;

    // nao muda a tile do tipo Grass
    public static Tile grass2 = new GrassTile(Sprite.grass2);
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile nulltile = new nullTile(Sprite.nullSprite);

    public Tile(Sprite sprite) {
        // TODO Auto-generated constructor stub
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
        // screen.renderTile(x*4, y*4, this);
    }

    public boolean solid() {
        return false;
    }

}
