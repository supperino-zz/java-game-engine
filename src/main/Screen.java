package main;

import java.util.Random;

import entity.Player;
import level.Tile;

public class Screen {

    private static final int MAPSIZE = 64;
    private static final int TILESIZE = 16;

    private int xOffset, yOffset;

    private Random random = new Random();

    private int[] tiles;

    public SpriteSheet sheet;

    public int[] pixels;
    public final int width, height;

    public Screen(int width, int height, int[] pixels) {
        // TODO Auto-generated constructor stub
        this.width = width;
        this.height = height;

        this.pixels = pixels;
        // 480x360 = 172.000 pixels
        tiles = new int[MAPSIZE * MAPSIZE];
    }

    /*
     * public void render(int xOffset, int yOffset) { // TODO Auto-generated method
     * stub // atualizar todo o vetor de pixels // gamepanel recebe todos os pixels
     * e os renderiza!
     * 
     * for (int y = 0; y < height; y++) { int yOff = y + yOffset; if (yOff < 0
     * ||yOff >= height) continue; for (int x = 0; x < width; x++) { int xOff = x +
     * xOffset; if (xOff < 0 || xOff >= width) continue; pixels[xOff + yOff *
     * width]= Sprite.grass.pixels_sprite[(x & 15) + (y & 15) * Sprite.SIZE]; } }
     * 
     * }
     */
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x000000;
        }
    }

    public void renderPlayer(int px, int py, Sprite sprite) {
        px -= xOffset;
        py -= yOffset;
        for (int y = 0; y < sprite.SIZE; y++) {
            // yAbsoluto (posição y absoluta = relação ao mapa) da tile
            int tya = y + py;
            for (int x = 0; x < sprite.SIZE; x++) {
                // xAbsoluto (posição yxabsoluta = relação ao mapa) da tile
                int txa = x + px;
                // apenas renderiza as tiles "visiveis" dentro do "tamanho" da tela
                // fiz essa modificação pra poder renderizar mais uma coluna de tiles à esquerda
                if (txa < -sprite.SIZE || txa >= width || tya < 0 || tya >= height)
                    break;
                if (txa < 0)
                    txa = 0;
                // dado a tile absoluta, vai receber ser preenchida com a tile. bem simples
                // tentando ignorar cor 0xff00ff
                //TO-DO
                if (sprite.pixels_sprite[x + y * sprite.SIZE] != 0xffff00ff) {
                    pixels[txa + tya * width] = sprite.pixels_sprite[x + y * sprite.SIZE];
                }

            }
        }
    }

    // tx = x posição da tile , ty = x posição da tile
    // quero saber onde a tile esta, e entao renderizar ela
    public void renderTile(int tx, int ty, Tile tile) {
        // atualiza o tx, ty (posição das tiles) com um offset (o mapa se move, e o
        // usuario fica no centro da tela)
        tx -= xOffset;
        ty -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            // yAbsoluto (posição y absoluta = relação ao mapa) da tile
            int tya = y + ty;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                // xAbsoluto (posição yxabsoluta = relação ao mapa) da tile
                int txa = x + tx;
                // apenas renderiza as tiles "visiveis" dentro do "tamanho" da tela
                if (txa < -tile.sprite.SIZE || txa >= width || tya < 0 || tya >= height)
                    break;
                if (txa < 0)
                    txa = 0;
                // dado a tile absoluta, vai receber ser preenchida com a tile. bem simples
                    
                pixels[txa + tya * width] = tile.sprite.pixels_sprite[x + y * tile.sprite.SIZE];

            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
