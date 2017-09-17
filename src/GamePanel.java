import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GamePanel extends Canvas implements Runnable{
	
	public  int WIDTH, HEIGHT;
	private boolean running;
	
	public Graphics g;
	private BufferStrategy buffer;
	private BufferedImage image;
	
	private int[] pixels;
	private SpriteSheet spritesheet;
	
	public InputHandler input;
	private Screen screen;
	
	public GamePanel(int width, int height) {
		/*
		 * Ignora os requests de paint/repaint do sistema operacional
		 * */
		WIDTH = width;
		HEIGHT = height;
		setMinimumSize(new Dimension(WIDTH,HEIGHT));
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setIgnoreRepaint( true );
	}
	
	public void initialize() {
		g = null;
		input = new InputHandler(this);
		//setSize(550, 450)
		createBufferStrategy(2);
		buffer = getBufferStrategy();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		spritesheet = new SpriteSheet("spritesheet.png");
		screen = new Screen(WIDTH, HEIGHT, spritesheet);
		

	}
	
	public void gameInput() {
		if(input.up.isPressed()) {screen.yOffset++;}
		if(input.down.isPressed()) {screen.yOffset--;}
		if(input.right.isPressed()) {screen.xOffset --;}
		if(input.left.isPressed()) {screen.xOffset ++;}
	}
	
	public void render() {
		try {
			g = buffer.getDrawGraphics();
			g.clearRect(0, 0, getWidth(), getHeight());
			if(!buffer.contentsLost()) {
				//buffer.show simplesmente troca as imagens de fundo com a principal
				screen.renderTile(pixels, 0, WIDTH);
				g.drawImage(image, 0, 0,getWidth(), getHeight(), null);
				buffer.show();
			}
			
		}finally {
			if(g != null) {
				//dispose exibe a imagem de g.
				g.dispose();
				//System.out.println("disposed");
			}
		}
	}
	
	public synchronized void start() {
		new Thread(this).start();
		running  = true;
		
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		initialize();
		double msperFrame = 1000/60;
		long lastMesured = System.currentTimeMillis();
		long startTime = System.currentTimeMillis();
		long mesuredTime = 0;
		int  i = 0;
		while(running) {
			mesuredTime = System.currentTimeMillis() - startTime;
			if(mesuredTime >= msperFrame) {
				gameInput();
				render();
				i++;
				startTime = System.currentTimeMillis();
				try {
					Thread.sleep(15);
				}catch (Exception e) {
					// TODO: handle exception
				}
				//System.out.println("kk");
			}
			if(System.currentTimeMillis() - lastMesured >= 1000) {
				System.out.println(i);
				i = 0;
				lastMesured = System.currentTimeMillis();
			}
		}
		
		
		//Enquanto o programa estiver rodando, chame a função render

		
	}
	
}
