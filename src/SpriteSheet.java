import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SpriteSheet {
	public String path;
	public int WIDTH, HEIGHT;
	public int[] pixels;
	
	public SpriteSheet(String path) {
		// TODO Auto-generated constructor stub
		BufferedImage image = null;
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(image==null) {
			return;
		}
		
		this.path = path;
		this.HEIGHT = image.getHeight();
		this.WIDTH = image.getWidth();
		//apenas imagens opacas, logo 0xAA(transparencia desconsiderado) RRGGBB
		pixels = image.getRGB(0, 0, WIDTH, HEIGHT, null, 0, WIDTH);
		
		for(int i = 0; i < pixels.length; i++) {
			//removendo todos os alfachannels
			//E, alem disso, vamos ter apenas 4 cores, o resto das cores será adicionado
			//"na ideia"... por código. Cada byte de cor tem 4 bits. 2^2 x 2 ^6 = 2 ^8
			//Logo, 255 (2 ^ 8 ) / x = 4   ->  x= 64
			pixels[i] = (pixels[i] & 0xFF)/64; 
			//
		}
		for(int i =0; i < 8; i++) {
			System.out.println(pixels[i]);
		}
	}
	
	
}
