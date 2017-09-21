package main;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int SIZE;
	public int[] pixels_ss;

	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);

	public SpriteSheet(String path, int size) {
		// TODO Auto-generated constructor stub
		this.path = path;
		SIZE = size;
		pixels_ss= new int[SIZE * SIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			image.getRGB(0, 0, SIZE, SIZE, pixels_ss, 0, SIZE);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
