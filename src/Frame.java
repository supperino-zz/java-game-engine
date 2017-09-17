import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Frame extends JFrame {
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 3;
	public static GamePanel gp;
	
	public Frame() {
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gp = new GamePanel(WIDTH*SCALE,HEIGHT*SCALE);
		add(gp);
		setLayout(new GridLayout(1,1,0,0));
		pack();
	}
	
	public static void main(String[] args) {
		new Frame();
		gp.start();
	}
}
