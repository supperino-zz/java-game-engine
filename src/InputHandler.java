import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {

	public InputHandler(GamePanel game) {
		game.addKeyListener(this);
	}
	
	public class Key{
		private boolean pressed;
		private int numTimesPressed = 0;
		
		public boolean isPressed() {
			return pressed;
		}
		
		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if(isPressed) {numTimesPressed++;}
		}
		
	}
		
	public Key up = new Key();
	public Key down = new Key();
	public Key right = new Key();
	public Key left = new Key();
	
	public void toggleKey(int keyCode, boolean isPressed) {
		if(keyCode == KeyEvent.VK_W) {up.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_S) {down.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_D) {right.toggle(isPressed);}
		if(keyCode == KeyEvent.VK_A) {left.toggle(isPressed);}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		toggleKey(e.getKeyCode(), true);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		toggleKey(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
