package level;

import main.Screen;;

public abstract class Level {
    /*
     * Basicamente um level tem uma lista de tiles com as caracteristicas de cada
     * tile e quando eu mando renderizar este level, ele só fala que cada tile tem
     * que se renderizar que por sua vez, vao passar seus deslocamntos e posições. E
     * , por fim, screen determina se a tile em questao esta dentro do "escopo" da
     * janela. Para entao ser renderizada ou nao.
     */

    // altura e largura em tiles
    protected int width, height;
    // todas as tiles terão inteiros que indicam qual tipo de tile é
    protected int[] map_tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        map_tiles = new int[width * height];

        generateLevel();
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        // x0 é o pixel
        screen.setOffset(xScroll, yScroll);
        // dividindo pelo tamanho da tile(16), é para acessar as tiles ao inves dos
        // pixels
        int x0 = (xScroll >> 4);
        // x1 é a coordenada x maxima da tela, dai divide para pegar por tiles
        int x1 = ((xScroll + screen.width) >> 4) + 1;
        // dividindo pelo tamanho da tile(16), é para acessar as tiles ao inves dos
        // pixels
        int y0 = (yScroll >> 4);
        // x1 é a coordenada x maxima da tela, dai divide para pegar por tiles
        int y1 = ((yScroll + screen.height) >> 4) + 1;

        /*
         * janela: [x0,y0 x1,y0] [ ] [x0,y1 x1,y1]
         */

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }

    }

    private Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.nulltile;
        }
        if (map_tiles[x + y * width] == 0) {
            return Tile.grass;
        }
        return Tile.nulltile;
    }

    public Level(String path) {
        loadLevel(path);
    }

    private void loadLevel(String path) {

    }

    protected void generateLevel() {
        // TODO Auto-generated method stub

    }

    // da update no mapa, posições... etc
    public void update() {

    }

    private void time() {

    }

}
