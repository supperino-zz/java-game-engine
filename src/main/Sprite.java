package main;

public class Sprite {

	public static int SIZE;
	private int x, y;
	public int[] pixels_sprite;
	private SpriteSheet sheet;

	public static Sprite grass2 = new Sprite(1, 0, 16, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(0, 0, 16, SpriteSheet.tiles);
	
	public static Sprite nullSprite = new Sprite(16, 0x1b87e0);
	/*public static Sprite[] player = new Sprite[] {new Sprite(0,13,16,SpriteSheet.tiles),
	                                              new Sprite(0,14,16,SpriteSheet.tiles),
	                                              new Sprite(0,15,16,SpriteSheet.tiles) 
	                                             };*/
	
	public static Sprite player0 = new Sprite(0,13,16,SpriteSheet.tiles);
	public static Sprite player1 = new Sprite(0,14,16,SpriteSheet.tiles);
	public static Sprite player2 = new Sprite(0,15,16,SpriteSheet.tiles);
	
	public Sprite(int x, int y, int size, SpriteSheet sheet) {
		// TODO Auto-generated constructor stub
		SIZE = size;
		this.x = x*SIZE;
		this.y = y*SIZE;
		this.sheet = sheet;
		
		pixels_sprite = new int[SIZE*SIZE];  
		
		load();
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels_sprite = new int [ SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for(int i = 0; i < pixels_sprite.length; i++) {
			pixels_sprite[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels_sprite[x + y * SIZE] = sheet.pixels_ss[(x + this.x) + ((y + this.y) * sheet.SIZE)];
			}
		}
	}

}
